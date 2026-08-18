package org.litvinov.taskarray.exception;

public class InvalidLineException extends Exception {
    public InvalidLineException(String message) {
        super(message);
    }

    public InvalidLineException(String message, Throwable cause) {
        super(message, cause);
    }
}
