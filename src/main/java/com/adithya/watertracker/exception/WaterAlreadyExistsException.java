package com.adithya.watertracker.exception;

public class WaterAlreadyExistsException extends RuntimeException {

    public WaterAlreadyExistsException(String message) {
        super(message);
    }
}