package com.adithya.watertracker.exception;

public class WaterEntryNotFoundException extends RuntimeException {

    public WaterEntryNotFoundException(String message) {
        super(message);
    }
}