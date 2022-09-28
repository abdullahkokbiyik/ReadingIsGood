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
    private long numOfBooks;
    private double orderCost;

    public GetOrdersOfCustomerDTO(Order order) {
        this.customerEmail = order.getCustomer().getEmail();
        this.bookName = order.getBook().getBookName();
        this.orderDate = order.getOrderDate();
        this.numOfBooks = order.getNumOfBooks();
        this.orderCost = order.getOrderCost();
    }
}
