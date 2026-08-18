package org.litvinov.taskarray.exception;


public class FileReadException extends Exception {

    public FileReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileReadException(String message) {
        super(message);
    }
}
