package org.litvinov.taskarray.parser.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.litvinov.taskarray.exception.InvalidLineException;
import org.litvinov.taskarray.parser.DoubleArrayParser;
import org.litvinov.taskarray.validator.ArrayLineValidator;
import org.litvinov.taskarray.validator.impl.ArrayLineValidatorImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DoubleArrayParserImplTest {

    private final ArrayLineValidator validator = new ArrayLineValidatorImpl();
    private final DoubleArrayParser parser = new DoubleArrayParserImpl(validator);

    private static Stream<Arguments> provideValidLines() {
        return Stream.of(
                Arguments.of("", new double[]{}),
                Arguments.of("   ", new double[]{}),
                Arguments.of("\t", new double[]{}),
                Arguments.of(" \t ", new double[]{}),

                Arguments.of(";", new double[]{}),
                Arguments.of(";;", new double[]{}),
                Arguments.of(" ; ; ", new double[]{}),
                Arguments.of(" ;\t; ", new double[]{}),

                Arguments.of("0", new double[]{0.0}),
                Arguments.of("00", new double[]{0.0}),
                Arguments.of("1.0", new double[]{1.0}),
                Arguments.of("1,0", new double[]{1.0}),
                Arguments.of("  -5  ", new double[]{-5.0}),
                Arguments.of("-3.14", new double[]{-3.14}),
                Arguments.of("-3,14", new double[]{-3.14}),

                Arguments.of("1;2;3", new double[]{1.0, 2.0, 3.0}),
                Arguments.of("1; -2; 3.5; -4,7", new double[]{1.0, -2.0, 3.5, -4.7}),
                Arguments.of(" 1 ; 2,5 ; -3.2 ", new double[]{1.0, 2.5, -3.2}),

                Arguments.of("1;;2", new double[]{1.0, 2.0}),
                Arguments.of("1; ; 2", new double[]{1.0, 2.0}),
                Arguments.of("1;;;2", new double[]{1.0, 2.0}),
                Arguments.of(";;1;;", new double[]{1.0}),
                Arguments.of("1;;;", new double[]{1.0}),
                Arguments.of(";;;1", new double[]{1.0}),
                Arguments.of("; ; ; 1 ; ;", new double[]{1.0}),

                Arguments.of(";1", new double[]{1.0}),
                Arguments.of("1;", new double[]{1.0}),
                Arguments.of(";1;", new double[]{1.0}),
                Arguments.of(";1;2;", new double[]{1.0, 2.0}),

                Arguments.of("1\t;\t2", new double[]{1.0, 2.0}),
                Arguments.of("1;2;\t3", new double[]{1.0, 2.0, 3.0}),
                Arguments.of(" \t 1 \t ; \t 2 \t ", new double[]{1.0, 2.0})
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidLines")
    void parseShouldReturnExpectedArray(String line, double[] expected) throws InvalidLineException {
        // given

        // when
        double[] result = parser.parse(line);

        // then
        assertArrayEquals(expected, result, 0.0001);
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
    void parseShouldThrowForInvalidLines(String line) {
        // given

        // when & then
        assertThrows(InvalidLineException.class, () -> parser.parse(line));
    }

    @Test
    void parseShouldReturnEmptyArrayForBlankLine() throws InvalidLineException {
        // given
        String line = "   ";

        // when
        double[] result = parser.parse(line);

        // then
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    @Test
    void parseShouldReturnEmptyArrayForEmptyString() throws InvalidLineException {
        // given
        String line = "";

        // when
        double[] result = parser.parse(line);

        // then
        assertNotNull(result);
        assertEquals(0, result.length);
    }
}