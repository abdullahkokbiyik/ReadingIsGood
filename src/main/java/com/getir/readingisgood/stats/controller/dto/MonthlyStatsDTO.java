package com.getir.readingisgood.stats.controller.dto;

import com.getir.readingisgood.order.entity.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Month;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MonthlyStatsDTO {
    private String month;
    private long totalOrderCount;
    private long totalBookCount;
    private double totalPurchasedAmount;

    public MonthlyStatsDTO(int month, List<Order> monthlyOrders) {
        this.month = Month.of(month).name();
        this.totalOrderCount = monthlyOrders.size();
        this.totalBookCount = monthlyOrders.stream().mapToLong(Order::getNumOfBooks).sum();
        this.totalPurchasedAmount = monthlyOrders.stream().mapToDouble(Order::getOrderCost).sum();
    }
}
