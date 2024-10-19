package com.example.coffeemachine.repository;

import com.example.coffeemachine.entity.dto.StatisticDTO;
import com.example.coffeemachine.entity.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    @Query("SELECT new com.example.coffeemachine.entity.dto.StatisticDTO(s.drink.name, COUNT(s)) " +
            "FROM Statistic s " +
            "GROUP BY s.drink.name " +
            "ORDER BY COUNT(s) DESC " +
            "LIMIT 1")
    StatisticDTO findMostPopularDrink();

    @Query("SELECT new com.example.coffeemachine.entity.dto.StatisticDTO(s.drink.name, COUNT(s)) " +
            "FROM Statistic s " +
            "WHERE s.orderDate BETWEEN :startDate AND :endDate " +
            "GROUP BY s.drink.name")
    List<StatisticDTO> findDrinkStatisticsByDateRange(@Param("startDate") LocalDate startDate,
                                                      @Param("endDate") LocalDate endDate);

    void deleteByOrderDateBefore(LocalDate date);
}
