package com.getir.readingisgood.unit.order.pojo;

import com.getir.readingisgood.common.AbstractUnitTest;
import com.getir.readingisgood.order.service.pojo.GetOrdersByDatePojo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestGetOrdersByDatePojo extends AbstractUnitTest {

    @Test
    public void testGetOrdersByDatePojo() {
        LocalDate now = LocalDate.now();
        
        GetOrdersByDatePojo getOrdersByDatePojo = new GetOrdersByDatePojo(now, now, 0, 5);

        Assertions.assertEquals(LocalDateTime.of(now, LocalTime.MIN), getOrdersByDatePojo.getStartDate());
        Assertions.assertEquals(LocalDateTime.of(now, LocalTime.MAX), getOrdersByDatePojo.getEndDate());
    }
}
