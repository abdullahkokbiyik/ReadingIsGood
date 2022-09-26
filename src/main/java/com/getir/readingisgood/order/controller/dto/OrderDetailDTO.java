package com.getir.readingisgood.order.controller.dto;

import com.getir.readingisgood.order.entity.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDTO {
    private Long customerId;
    private String customerEmail;
    private Long bookId;
    private String bookName;
    private Long numOfBooks;

    public OrderDetailDTO(Order order) {
        this.customerId = order.getCustomer().getId();
        this.customerEmail = order.getCustomer().getEmail();
        this.bookId = order.getBook().getId();
        this.bookName = order.getBook().getName();
        this.numOfBooks = order.getNumOfBooks();
    }
}