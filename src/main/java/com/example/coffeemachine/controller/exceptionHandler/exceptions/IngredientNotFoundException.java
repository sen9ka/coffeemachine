package com.example.coffeemachine.controller.exceptionHandler.exceptions;

public class IngredientNotFoundException extends RuntimeException {
    public IngredientNotFoundException(String message) {
        super(message);
    }
}
