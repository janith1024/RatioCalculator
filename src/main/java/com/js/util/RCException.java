package com.js.util;



public class RCException extends RuntimeException {

    public RCException(String message) {
        super(message);
    }

    public RCException(String message, Throwable cause) {
        super(message, cause);
    }
}
