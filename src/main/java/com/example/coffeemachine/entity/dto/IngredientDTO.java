package com.example.coffeemachine.entity.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class IngredientDTO {

    private Long id;

    @NotEmpty(message = "Drink name should not be empty")
    private String name;

    private Integer quantity;

}
