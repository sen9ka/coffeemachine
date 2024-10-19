package com.example.coffeemachine.entity.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrinkDTO {

    private Long id;

    @Size(min = 3, message = "Drink name should contain at least 3 characters")
    private String name;

    private Boolean isStandard;

    private List<IngredientDTO> ingredientList;

}
