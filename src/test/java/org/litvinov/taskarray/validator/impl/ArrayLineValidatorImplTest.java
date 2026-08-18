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
            "   ",
            "42",
            "3.14",
            "3,14",
            "-5",
            "1; 2; 3",
            " 1 ; 2,5 ; -3.2 ",
            "; 1; 2;",
            "1; ",
            ""
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
            "1y1 21 32",
            "1, 2, 3",
            ";",
            ";  ;",
            "1.2.3",
            "1;;3"
    })
    void isValidShouldReturnFalseForInvalidLines(String line) {
        // given

        // when
        boolean result = validator.isValid(line);

        // then
        assertFalse(result);
    }
}
