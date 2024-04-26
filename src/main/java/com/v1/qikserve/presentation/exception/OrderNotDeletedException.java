package com.v1.qikserve.presentation.exception;

public class OrderNotDeletedException extends RuntimeException {
    public OrderNotDeletedException(String id) {
        super("Could not delete the order: " + id);
    }
}
