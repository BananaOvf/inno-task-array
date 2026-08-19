package org.litvinov.taskarray.validator.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.litvinov.taskarray.validator.ArrayLineValidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayLineValidatorImplTest {

    private final ArrayLineValidator validator = new ArrayLineValidatorImpl();

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "   ",
            "\t",
            " \t ",

            ";",
            ";;",
            " ; ; ",
            " ;\t; ",

            "0",
            "00",
            "1.0",
            "1,0",
            "  -5  ",
            "-3.14",
            "-3,14",

            "1;2;3",
            "1; -2; 3.5; -4,7",
            " 1 ; 2,5 ; -3.2 ",

            "1;;2",
            "1; ; 2",
            "1;;;2",
            ";;1;;",
            "1;;;",
            ";;;1",
            "; ; ; 1 ; ;",

            ";1",
            "1;",
            ";1;",
            ";1;2;",

            "1\t;\t2",
            "1;2;\t3",
            " \t 1 \t ; \t 2 \t "
    })
    void isValidShouldReturnTrueForValidLines(String line) {
        // given

        // when
        boolean result = validator.isValid(line);

        // then
        assertTrue(result);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            "1 2",
            " 1 2 ",
            "1\t2",

            "1, 2, 3",
            "1,2,3",
            "1, 2; 3",

            "1.2.3",
            "1,2,3",
            "1; 2.3.4",
            "1; 2,3,4",
            "1; 2.3,4",
            "1; 2,3.4",

            ".5",
            "5.",
            "-.",
            "1; .5",
            "1; 5.",

            "+5",
            "1; +2",

            "1e3",
            "1; 2e3",

            "1y1 21 32",
            "1; two",
            "1; 2_000",
            "1; 0x10",
            "1; NaN",
            "1; Infinity",

            "1; - 2",
            "- 5",

            "1; 2 3",
            "1; 2; 3 4",
            "1; 2; 3,4,5",

            "1; 2; 3abc",
            "abc1",
            "1; 2; #"
    })
    void isValidShouldReturnFalseForInvalidLines(String line) {
        // given

        // when
        boolean result = validator.isValid(line);

        // then
        assertFalse(result);
    }
}
