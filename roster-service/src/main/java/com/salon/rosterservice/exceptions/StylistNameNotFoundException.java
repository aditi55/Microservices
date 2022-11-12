package com.salon.rosterservice.exceptions;


public class StylistNameNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public StylistNameNotFoundException(String msg) {
        super(msg);
    }
}
