package com.getir.readingisgood.order.service;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.service.BookService;
import com.getir.readingisgood.common.annotations.QueryEntityLogger;
import com.getir.readingisgood.common.annotations.SaveEntityLogger;
import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.repository.OrderRepository;
import com.getir.readingisgood.order.service.checker.OrderChecker;
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
    private final OrderChecker orderChecker;

    @Transactional(readOnly = true)
    @QueryEntityLogger
    public List<Order> getOrdersOfCustomer(GetOrdersOfCustomersPojo getOrdersOfCustomersPojo) {
        return orderRepository.getPaginatedOrdersByCustomerId(getOrdersOfCustomersPojo);
    }

    @Transactional(readOnly = true)
    @QueryEntityLogger
    public Order getOrderById(Long id) {
        if (orderChecker.checkOrderExists(id)) {
            return orderRepository.getById(id);
        }
        return new Order();
    }

    @Transactional
    @SaveEntityLogger
    public void add(List<Order> orders) {
        if (orderChecker.checkForAddOrder(orders)) {
            for (Order order : orders) {
                setOrderCost(order);
                orderRepository.add(order);
                bookService.decreaseStockAmount(order.getBook().getId(), order.getNumOfBooks());
            }
        }
    }

    @Transactional(readOnly = true)
    @QueryEntityLogger
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
