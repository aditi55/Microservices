package com.salon.appointment.exception;

public class StylistNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public StylistNotFoundException(String message) {
        super(message);
    }

    public StylistNotFoundException() {
    }
}
