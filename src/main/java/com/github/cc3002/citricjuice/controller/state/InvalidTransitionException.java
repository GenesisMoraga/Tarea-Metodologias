package com.github.cc3002.citricjuice.controller.state;

public class InvalidTransitionException extends Exception {
    public InvalidTransitionException(String message) {
        super(message);
    }
}
