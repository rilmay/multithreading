package com.epam.multithreading.exception;

public class MultithreadingException extends RuntimeException {
    public MultithreadingException() {
    }

    public MultithreadingException(String message) {
        super(message);
    }

    public MultithreadingException(Throwable cause) {
        super(cause);
    }
}
