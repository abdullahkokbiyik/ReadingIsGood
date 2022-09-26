package com.getir.readingisgood.order.entity;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.common.entity.AbstractEntity;
import com.getir.readingisgood.customer.entity.Customer;
import com.getir.readingisgood.order.controller.dto.BookOrderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends AbstractEntity {

    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @Column(name = "num_of_books")
    private Long numOfBooks;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    public Order(Long customerId, BookOrderDTO bookOrder, LocalDateTime orderDate) {
        this.customer = new Customer(customerId);
        this.book = new Book(bookOrder.getBookId());
        this.numOfBooks = bookOrder.getNumOfBook();
        this.orderDate = orderDate != null ? orderDate : LocalDateTime.now();
    }
}
