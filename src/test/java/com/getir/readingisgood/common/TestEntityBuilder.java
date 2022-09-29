package com.getir.readingisgood.common;

import com.getir.readingisgood.author.entity.Author;
import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.customer.entity.Customer;
import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.service.pojo.GetOrdersByDatePojo;
import com.getir.readingisgood.order.service.pojo.GetOrdersOfCustomersPojo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class TestEntityBuilder {
    public static Book createBook(Long id, Long authorId) {
        return new Book(id, "BookName", 20L, 20., createAuthor(authorId), UUID.randomUUID().toString());
    }

    public static Author createAuthor(Long id) {
        return new Author(id, "AuthorName", "AuthorSurname", "AuthorNat", UUID.randomUUID().toString());
    }

    public static Customer createCustomer(Long id) {
        return new Customer(id, "CustomerName", "customermail@outlook.com");
    }

    public static GetOrdersOfCustomersPojo createGetOrdersOfCustomersPojo(Long customerId) {
        return new GetOrdersOfCustomersPojo(customerId, 0, 10);
    }

    public static Order createOrder(Long id, Long bookId, Long authorId, Long customerId) {
        return new Order(id, createCustomer(customerId), createBook(bookId, authorId), 5L, LocalDateTime.now(), 100.);
    }

    public static GetOrdersByDatePojo createGetOrdersByDatePojo(LocalDate startDate, LocalDate endDate) {
        return new GetOrdersByDatePojo(startDate, endDate, 0, 10);
    }
}
