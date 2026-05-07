package com.rewards.api.exception;

/**
 * Custom exception for resource not found.
 */
public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
