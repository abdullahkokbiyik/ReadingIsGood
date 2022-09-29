package com.getir.readingisgood.order.entity;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.customer.entity.Customer;
import com.getir.readingisgood.order.controller.dto.BookOrderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
    private Customer customer;

    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Book.class)
    private Book book;

    @Column(name = "num_of_books")
    private Long numOfBooks;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "order_cost")
    private Double orderCost;

    public Order(Long customerId, BookOrderDTO bookOrder, LocalDateTime orderDate) {
        this.customer = new Customer(customerId);
        this.book = new Book(bookOrder.getBookId());
        this.numOfBooks = bookOrder.getNumOfBook();
        this.orderDate = orderDate != null ? orderDate : LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "{ Id: " + this.getId() + ", Customer: " + this.customer + ", Book: " + this.book +
                ", Number of Books: " + this.numOfBooks + ", Order Date: " + this.orderDate + ", Total Cost: " + this.orderCost + " }";
    }
}
