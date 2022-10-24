package com.upstack.test;

class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
