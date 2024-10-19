package com.example.coffeemachine.controller.exceptionHandler.exceptions;

public class DrinkAlreadyExistException extends RuntimeException {
    public DrinkAlreadyExistException(String message) {
        super(message);
    }
}
