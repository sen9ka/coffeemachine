package com.example.coffeemachine.controller;

import com.example.coffeemachine.entity.dto.DrinkDTO;
import com.example.coffeemachine.service.DrinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drinks")
@Tag(name = "Контроллер для заказа кофе")
public class DrinkController {

    private final DrinkService drinkService;

    @GetMapping
    @Operation(summary = "Получение списка всех напитков")
    public ResponseEntity<List<DrinkDTO>> getAllDrinks() {
        return new ResponseEntity<>(drinkService.findAllDrinks(), HttpStatus.OK);
    }

    @GetMapping("/standard")
    @Operation(summary = "Получение списка стандартных напитков")
    public ResponseEntity<List<DrinkDTO>> getStandardDrinks() {
        return new ResponseEntity<>(drinkService.findStandardDrinks(), HttpStatus.OK);
    }

    @PostMapping("/order/{drinkId}")
    @Operation(summary = "Приготовление напитка")
    public ResponseEntity<String> orderDrink(@PathVariable Long drinkId) {
        return new ResponseEntity<>(drinkService.orderDrink(drinkId), HttpStatus.OK);
    }

    @PostMapping("/new")
    @Operation(summary = "Добавление нового напитка")
    public ResponseEntity<DrinkDTO> createDrink(
            @Valid @RequestBody DrinkDTO drinkDTO
    ) {
        return new ResponseEntity<>(drinkService.createDrink(drinkDTO), HttpStatus.CREATED);
    }

}
