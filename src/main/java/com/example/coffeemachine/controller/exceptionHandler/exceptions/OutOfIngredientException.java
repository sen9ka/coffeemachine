package com.example.coffeemachine.controller.exceptionHandler.exceptions;

public class OutOfIngredientException extends RuntimeException {
    public OutOfIngredientException(String message) {
        super(message);
    }
}
