package com.example.coffeemachine.service;

import com.example.coffeemachine.entity.dto.StatisticDTO;
import com.example.coffeemachine.repository.StatisticRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final StatisticRepository statisticRepository;

    @Transactional
    public StatisticDTO getMostPopularDrink() {
        return statisticRepository.findMostPopularDrink();
    }

    @Transactional
    public List<StatisticDTO> getDrinkStatisticsByDateRange(LocalDate startDate, LocalDate endDate) {
        return statisticRepository.findDrinkStatisticsByDateRange(startDate, endDate);
    }

    // Удаление записей статистики, которым больше 5 лет, раз в полночь
    @Scheduled(cron = "0 0 0 * * ?")
    public void removeOldStatistics() {
        LocalDate fiveYearsAgo = LocalDate.now().minusYears(5);
        statisticRepository.deleteByOrderDateBefore(fiveYearsAgo);
    }
}