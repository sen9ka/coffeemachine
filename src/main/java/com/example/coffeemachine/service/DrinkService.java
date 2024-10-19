package com.example.coffeemachine.service;

import com.example.coffeemachine.controller.exceptionHandler.Constant;
import com.example.coffeemachine.controller.exceptionHandler.exceptions.DrinkAlreadyExistException;
import com.example.coffeemachine.controller.exceptionHandler.exceptions.OutOfIngredientException;
import com.example.coffeemachine.entity.dto.DrinkDTO;
import com.example.coffeemachine.entity.dto.IngredientDTO;
import com.example.coffeemachine.entity.model.Drink;
import com.example.coffeemachine.entity.model.DrinkIngredient;
import com.example.coffeemachine.entity.model.Ingredient;
import com.example.coffeemachine.entity.model.Statistic;
import com.example.coffeemachine.repository.DrinkIngredientsRepository;
import com.example.coffeemachine.repository.DrinkRepository;
import com.example.coffeemachine.repository.IngredientRepository;
import com.example.coffeemachine.repository.StatisticRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrinkService {

    private final DrinkRepository drinkRepository;
    private final DrinkIngredientsRepository drinkIngredientsRepository;
    private final IngredientRepository ingredientRepository;
    private final StatisticRepository statisticRepository;
    private final IngredientService ingredientService;

    private final AtomicBoolean isOrderInProgress = new AtomicBoolean(false);

    private DrinkDTO convertToDrinkDTO(Drink drink) {
        DrinkDTO drinkDTO = new DrinkDTO();
        BeanUtils.copyProperties(drink, drinkDTO);
        return drinkDTO;
    }

    @Transactional
    public List<DrinkDTO> findStandardDrinks() {
        List<Drink> drinks = drinkRepository.findDrinkByIsStandardTrue();
        return drinks.stream().map(this::convertToDrinkDTO).collect(Collectors.toList());
    }

    @Transactional
    public List<DrinkDTO> findAllDrinks() {
        List<Drink> drinks = drinkRepository.findAll();
        return drinks.stream().map(this::convertToDrinkDTO).collect(Collectors.toList());
    }

    @Transactional
    public String orderDrink(Long drinkId) {
        if (isOrderInProgress.get()) {
            return Constant.ORDER_IN_PROGRESS_MESSAGE;
        }

        isOrderInProgress.set(true);
        prepareDrinkAsync(drinkId);
        return Constant.ORDER_ACCEPTED_MESSAGE;
    }

    @Async
    @Transactional
    public void prepareDrinkAsync(Long drinkId) {
        try {
            // Имитируем то, что заказ выполняется 2 минуты
            Thread.sleep(120000);
            prepareDrink(drinkId);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            isOrderInProgress.set(false);
        }
    }

    @Transactional
    public void prepareDrink(Long drinkId) {
        List<DrinkIngredient> drinkIngredients = drinkIngredientsRepository.findByDrinkId(drinkId);
        Drink drink = drinkIngredients.get(0).getDrink();
        for (DrinkIngredient drinkIngredient : drinkIngredients) {
            Ingredient ingredient = drinkIngredient.getIngredient();

            // Проверить, достаточно ли ингредиента
            if (ingredient.getQuantity() < drinkIngredient.getQuantity()) {
                throw new OutOfIngredientException(Constant.OUT_OF_INGREDIENT_EXCEPTION_MESSAGE + ingredient.getName());
            }

            // Вычитаем количество ингредиента
            ingredient.setQuantity(ingredient.getQuantity() - drinkIngredient.getQuantity());
            ingredientRepository.save(ingredient);
        }

        // Сохраняем заказанный напиток в статистику
        Statistic statistic = new Statistic().toBuilder()
                .drink(drink)
                .orderDate(LocalDate.now())
                .build();
        statisticRepository.save(statistic);
    }

    @Transactional
    public DrinkDTO createDrink(DrinkDTO drinkDTO) {
        if (drinkRepository.findByNameIgnoreCase(drinkDTO.getName()).isPresent()) {
            throw new DrinkAlreadyExistException(Constant.DRINK_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        }

        Drink drink = new Drink();
        BeanUtils.copyProperties(drinkDTO, drink);
        drink = drinkRepository.save(drink);

        for (IngredientDTO ingredientDTO : drinkDTO.getIngredientList()) {
            DrinkIngredient drinkIngredient = new DrinkIngredient().toBuilder()
                    .drink(drink)
                    .ingredient(ingredientService.convertToIngredient(ingredientDTO))
                    .quantity(ingredientDTO.getQuantity())
                    .build();
            drinkIngredientsRepository.save(drinkIngredient);
        }

        DrinkDTO createdDrinkDTO = convertToDrinkDTO(drink);
        createdDrinkDTO.setIngredientList(drinkDTO.getIngredientList());

        return createdDrinkDTO;
    }
}
