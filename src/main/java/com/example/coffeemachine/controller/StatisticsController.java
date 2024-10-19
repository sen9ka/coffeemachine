package com.example.coffeemachine.controller;

import com.example.coffeemachine.entity.dto.StatisticDTO;
import com.example.coffeemachine.service.StatisticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
@Tag(name = "Контроллер для отслеживания статистики")
public class StatisticsController {

    private final StatisticService statisticService;

    @GetMapping("/all")
    @Operation(summary = "Получение статистики о заказах на промежутке дат")
    public ResponseEntity<List<StatisticDTO>> getDrinkStatisticsByDateRange(
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate
    ) {
        return new ResponseEntity<>(statisticService.getDrinkStatisticsByDateRange(startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/most-popular")
    @Operation(summary = "Получение информации о самом популярном напитке")
    public ResponseEntity<StatisticDTO>  getMostPopularDrink() {
        return new ResponseEntity<>(statisticService.getMostPopularDrink(), HttpStatus.OK);
    }

}
