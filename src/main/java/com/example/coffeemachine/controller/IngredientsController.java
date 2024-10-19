package com.example.coffeemachine.controller;

import com.example.coffeemachine.entity.dto.IngredientDTO;
import com.example.coffeemachine.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ingredients")
@Tag(name = "Контроллер для отслеживания ингредиентов")
public class IngredientsController {

    private final IngredientService ingredientService;

    @GetMapping
    @Operation(summary = "Получение списка оставшихся ингредиентов")
    public ResponseEntity<List<IngredientDTO>> getAllIngredients() {
        return new ResponseEntity<>(ingredientService.getIngredientsList(), HttpStatus.OK);
    }

    @GetMapping("{ingredientId}")
    @Operation(summary = "Получение информации об ингредиенте")
    public ResponseEntity<IngredientDTO> getOneIngredient(@PathVariable Long ingredientId) {
        return new ResponseEntity<>(ingredientService.getOneIngredient(ingredientId), HttpStatus.OK);
    }

    @PostMapping("{ingredientId}/{amount}")
    @Operation(summary = "Изменение количества ингредиента")
    public ResponseEntity<IngredientDTO> changeQuantityOfIngredients(
            @PathVariable Long ingredientId,
            @PathVariable Integer amount
    ) {
        return new ResponseEntity<>(ingredientService.changeQuantity(ingredientId, amount), HttpStatus.OK);
    }

}
