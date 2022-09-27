package com.getir.readingisgood.stats.controller;

import com.getir.readingisgood.stats.controller.dto.MonthlyStatsDTO;
import com.getir.readingisgood.stats.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
@Validated
public class StatsController {

    private final StatsService statsService;

    @GetMapping(value = "/getCustomersMonthlyStats")
    public List<MonthlyStatsDTO> getCustomersMonthlyStats(@RequestParam("customerId") @Min(1) Long customerId) {
        return statsService.getCustomersMonthlyStats(customerId);
    }
}
