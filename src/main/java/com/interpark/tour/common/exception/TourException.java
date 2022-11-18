package com.interpark.tour.common.exception;

public class TourException extends RuntimeException {
    public TourException() {
    }

    public TourException(String message) {
        super(message);
    }
}
