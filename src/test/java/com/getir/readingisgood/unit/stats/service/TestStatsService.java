package com.getir.readingisgood.unit.stats.service;

import com.getir.readingisgood.common.AbstractUnitTest;
import com.getir.readingisgood.common.TestEntityBuilder;
import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.repository.OrderRepository;
import com.getir.readingisgood.stats.controller.dto.MonthlyStatsDTO;
import com.getir.readingisgood.stats.service.StatsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TestStatsService extends AbstractUnitTest {

    @InjectMocks
    private StatsService statsService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    public void testGetCustomersMonthlyStats() {
        Long customerId = 1L;
        Order firstOrder = TestEntityBuilder.createOrder(1L, 1L, 1L, customerId);
        Order secondOrder = TestEntityBuilder.createOrder(2L, 2L, 2L, customerId);

        Mockito.when(orderRepository.getOrdersByCustomer(customerId)).thenReturn(Arrays.asList(firstOrder, secondOrder));

        List<MonthlyStatsDTO> monthlyStatsDTOList = statsService.getCustomersMonthlyStats(customerId);

        MonthlyStatsDTO monthlyStatsDTO = monthlyStatsDTOList.get(0);
        Assertions.assertEquals(monthlyStatsDTO.getMonth(), LocalDate.now().getMonth().name());
        Assertions.assertEquals(monthlyStatsDTO.getTotalBookCount(), firstOrder.getNumOfBooks() + secondOrder.getNumOfBooks());
        Assertions.assertEquals(monthlyStatsDTO.getTotalPurchasedAmount(), firstOrder.getOrderCost() + secondOrder.getOrderCost());

        Mockito.verify(orderRepository).getOrdersByCustomer(customerId);
    }
}
