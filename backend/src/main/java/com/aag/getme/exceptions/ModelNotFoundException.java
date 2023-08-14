package com.aag.getme.exceptions;

public class ModelNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1l;

    public ModelNotFoundException(String msg) {
        super(msg);
    }

}
