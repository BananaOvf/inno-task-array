package org.litvinov.taskarray.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ExceptionsTest {

    private static final String MESSAGE = "test";
    private static final String CAUSE = "cause";

    @Test
    void arrayWrapperExceptionShouldHaveMessage() {
        // given & when
        ArrayWrapperException exception = new ArrayWrapperException(MESSAGE);

        // then
        assertEquals(MESSAGE, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void arrayWrapperExceptionShouldHaveMessageAndCause() {
        // given
        Throwable cause = new RuntimeException(CAUSE);

        // when
        ArrayWrapperException exception = new ArrayWrapperException(MESSAGE, cause);

        // then
        assertEquals(MESSAGE, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void fileReadExceptionShouldHaveMessage() {
        // given & when
        FileReadException exception = new FileReadException(MESSAGE);

        // then
        assertEquals(MESSAGE, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void fileReadExceptionShouldHaveMessageAndCause() {
        // given
        Throwable cause = new RuntimeException(CAUSE);

        // when
        FileReadException exception = new FileReadException(MESSAGE, cause);

        // then
        assertEquals(MESSAGE, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void invalidLineExceptionShouldHaveMessage() {
        // given & when
        InvalidLineException exception = new InvalidLineException(MESSAGE);

        // then
        assertEquals(MESSAGE, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void invalidLineExceptionShouldHaveMessageAndCause() {
        // given
        Throwable cause = new RuntimeException(CAUSE);

        // when
        InvalidLineException exception = new InvalidLineException(MESSAGE, cause);

        // then
        assertEquals(MESSAGE, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}