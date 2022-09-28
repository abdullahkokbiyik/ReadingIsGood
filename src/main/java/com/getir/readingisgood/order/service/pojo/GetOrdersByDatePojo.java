package com.getir.readingisgood.order.service.pojo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class GetOrdersByDatePojo {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int pageNum;
    private int pageSize;

    public GetOrdersByDatePojo(LocalDate startDate, LocalDate endDate, int pageNum, int pageSize) {
        this.startDate = LocalDateTime.of(startDate, LocalTime.MIN);
        this.endDate = LocalDateTime.of(endDate, LocalTime.MAX);
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
