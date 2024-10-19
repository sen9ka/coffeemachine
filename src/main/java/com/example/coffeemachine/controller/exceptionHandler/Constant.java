package com.example.coffeemachine.controller.exceptionHandler;

public class Constant {
    public static final String OUT_OF_INGREDIENT_EXCEPTION_MESSAGE = "Необходимый ингредиент закончился: ";
    public static final String INGREDIENT_NOT_FOUND_EXCEPTION_MESSAGE = "Ингредиент не найден";
    public static final String INGREDIENT_QUANTITY_BELOW_ZERO = "Количество ингредиента не может быть меньше нуля";
    public static final String DRINK_ALREADY_EXISTS_EXCEPTION_MESSAGE = "Напиток с таким названием уже есть в системе";
    public static final String ORDER_IN_PROGRESS_MESSAGE = "Предыдущий заказ еще выполняется. Пожалуйста, подождите.";
    public static final String ORDER_ACCEPTED_MESSAGE = "Ваш заказ принят. Приготовление начнется.";
}
