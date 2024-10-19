package com.example.coffeemachine.controller.exceptionHandler.exceptions;

public class DrinkNotFoundException extends RuntimeException {
    public DrinkNotFoundException(String message) {
        super(message);
    }
}
