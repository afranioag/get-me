package com.aag.getme.exceptions;

public class InvalidResourceAccessException extends RuntimeException {
    private static final long serialVersionUID = 1l;

    public InvalidResourceAccessException(String msg) {
        super(msg);
    }

}
