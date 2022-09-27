package com.getir.readingisgood.order.service;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.service.BookService;
import com.getir.readingisgood.book.service.checker.BookChecker;
import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.repository.OrderRepository;
import com.getir.readingisgood.order.service.pojo.GetOrdersOfCustomersPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookService bookService;
    private final BookChecker bookChecker;

    @Transactional(readOnly = true)
    public List<Order> getOrdersOfCustomer(GetOrdersOfCustomersPojo getOrdersOfCustomersPojo) {
        return orderRepository.getPaginatedOrdersByCustomerId(getOrdersOfCustomersPojo);
    }

    @Transactional(readOnly = true)
    public Order getOrderById(Long id) {
        return orderRepository.getById(id);
    }

    @Transactional
    public void add(List<Order> orders) {
        if (orders.stream().allMatch(order -> bookChecker.checkExists(order.getBook().getId()))) {
            for (Order order : orders) {
                setOrderCost(order);
                orderRepository.add(order);
                bookService.decreaseStockAmount(order.getBook().getId(), order.getNumOfBooks());
            }
        }
    }

    @Transactional(readOnly = true)
    public List<Order> getOrdersByDate(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.MIN);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.MAX);
        return orderRepository.getByDate(startDateTime, endDateTime);
    }

    private void setOrderCost(Order order) {
        Book book = bookService.getBookById(order.getBook().getId());
        double cost = order.getNumOfBooks() * book.getCost();
        order.setOrderCost(cost);
    }
}
