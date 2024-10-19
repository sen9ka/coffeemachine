package com.example.coffeemachine.repository;

import com.example.coffeemachine.entity.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
    Optional<Drink> findByNameIgnoreCase(String name);
    List<Drink> findDrinkByIsStandardTrue();
}
