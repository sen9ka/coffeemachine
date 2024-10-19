package com.example.coffeemachine.service;

import com.example.coffeemachine.controller.exceptionHandler.Constant;
import com.example.coffeemachine.controller.exceptionHandler.exceptions.IngredientNotFoundException;
import com.example.coffeemachine.controller.exceptionHandler.exceptions.OutOfIngredientException;
import com.example.coffeemachine.entity.dto.IngredientDTO;
import com.example.coffeemachine.entity.model.Ingredient;
import com.example.coffeemachine.repository.IngredientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientDTO convertToIngredientDTO(Ingredient ingredient) {
        IngredientDTO ingredientDTO = new IngredientDTO();
        BeanUtils.copyProperties(ingredient, ingredientDTO);
        return ingredientDTO;
    }

    public Ingredient convertToIngredient(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient();
        BeanUtils.copyProperties(ingredientDTO, ingredient);
        return ingredient;
    }

    @Transactional
    public List<IngredientDTO> getIngredientsList() {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        return ingredients.stream().map(this::convertToIngredientDTO).collect(Collectors.toList());
    }

    @Transactional
    public IngredientDTO getOneIngredient(Long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IngredientNotFoundException(Constant.INGREDIENT_NOT_FOUND_EXCEPTION_MESSAGE));
        return convertToIngredientDTO(ingredient);
    }

    @Transactional
    public IngredientDTO changeQuantity(Long ingredientId, Integer amount) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IngredientNotFoundException(Constant.INGREDIENT_NOT_FOUND_EXCEPTION_MESSAGE));
        ingredient.setQuantity(ingredient.getQuantity() + amount);

        if (ingredient.getQuantity() < 0)
            throw new OutOfIngredientException(Constant.INGREDIENT_QUANTITY_BELOW_ZERO);

        ingredientRepository.save(ingredient);
        return convertToIngredientDTO(ingredient);
    }
}
