package com.getir.readingisgood.stats.service;

import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.repository.OrderRepository;
import com.getir.readingisgood.stats.controller.dto.MonthlyStatsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<MonthlyStatsDTO> getCustomersMonthlyStats(Long customerId) {
        List<MonthlyStatsDTO> monthlyStatsDTOList = new ArrayList<>();
        List<Order> orders = orderRepository.getOrdersByCustomer(customerId);
        Map<Integer, List<Order>> orderGroup = orders.stream().collect(Collectors.groupingBy(order -> order.getOrderDate().getMonthValue()));
        for (Integer month : orderGroup.keySet()) {
            monthlyStatsDTOList.add(new MonthlyStatsDTO(month, orderGroup.get(month)));
        }
        return monthlyStatsDTOList;
    }
}
