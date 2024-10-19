package com.example.coffeemachine.repository;

import com.example.coffeemachine.entity.model.DrinkIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkIngredientsRepository extends JpaRepository<DrinkIngredient, Long> {
    List<DrinkIngredient> findByDrinkId(Long drinkId);
}
