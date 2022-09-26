package com.getir.readingisgood.customer.controller.dto;

import com.getir.readingisgood.order.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetOrdersOfCustomerDTO {
    private String customerEmail;
    private String bookName;
    private LocalDateTime orderDate;

    public GetOrdersOfCustomerDTO(Order order) {
        this.customerEmail = order.getCustomer().getEmail();
        this.bookName = order.getBook().getName();
        this.orderDate = order.getOrderDate();
    }
}
