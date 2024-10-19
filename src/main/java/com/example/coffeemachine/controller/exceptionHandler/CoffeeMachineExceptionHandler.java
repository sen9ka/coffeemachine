package com.example.coffeemachine.controller.exceptionHandler;

import com.example.coffeemachine.controller.exceptionHandler.exceptions.DrinkAlreadyExistException;
import com.example.coffeemachine.controller.exceptionHandler.exceptions.DrinkNotFoundException;
import com.example.coffeemachine.controller.exceptionHandler.exceptions.IngredientNotFoundException;
import com.example.coffeemachine.controller.exceptionHandler.exceptions.OutOfIngredientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CoffeeMachineExceptionHandler {

    @ExceptionHandler(DrinkNotFoundException.class)
    public ResponseEntity<Object> handleDrinkNotFoundException(DrinkNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OutOfIngredientException.class)
    public ResponseEntity<Object> handleOutOfIngredientException(OutOfIngredientException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IngredientNotFoundException.class)
    public ResponseEntity<Object> handleIngredientNotFoundException(IngredientNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DrinkAlreadyExistException.class)
    public ResponseEntity<Object> handleDrinkAlreadyExistException(DrinkAlreadyExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
