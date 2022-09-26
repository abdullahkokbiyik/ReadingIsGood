package com.getir.readingisgood.order.service;

import com.getir.readingisgood.book.service.BookService;
import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.repository.OrderRepository;
import com.getir.readingisgood.order.service.pojo.GetOrdersOfCustomersPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookService bookService;

    @Transactional(readOnly = true)
    public List<Order> getOrdersOfCustomer(GetOrdersOfCustomersPojo getOrdersOfCustomersPojo) {
        return orderRepository.getOrdersByCustomerId(getOrdersOfCustomersPojo);
    }

    @Transactional(readOnly = true)
    public Order getOrderById(Long id) {
        return orderRepository.getById(id);
    }

    @Transactional
    public void add(List<Order> orders) {
        for (Order order : orders) {
            orderRepository.add(order);
            bookService.decreaseStockAmount(order.getBook().getId(), order.getNumOfBooks());
        }
    }
}
