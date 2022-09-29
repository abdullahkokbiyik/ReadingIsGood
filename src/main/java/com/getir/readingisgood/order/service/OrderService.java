package com.getir.readingisgood.order.service;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.service.BookService;
import com.getir.readingisgood.common.annotations.QueryEntityLogger;
import com.getir.readingisgood.common.annotations.SaveEntityLogger;
import com.getir.readingisgood.customer.controller.dto.GetOrdersOfCustomerDTO;
import com.getir.readingisgood.order.controller.dto.OrderDetailDTO;
import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.repository.OrderRepository;
import com.getir.readingisgood.order.service.checker.OrderChecker;
import com.getir.readingisgood.order.service.pojo.GetOrdersByDatePojo;
import com.getir.readingisgood.order.service.pojo.GetOrdersOfCustomerPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookService bookService;
    private final OrderChecker orderChecker;

    @Transactional(readOnly = true)
    @QueryEntityLogger
    public List<GetOrdersOfCustomerDTO> getOrdersOfCustomer(Long customerId, int pageNum, int pageSize) {
        GetOrdersOfCustomerPojo getOrdersOfCustomerPojo = new GetOrdersOfCustomerPojo(customerId, pageNum, pageSize);
        List<Order> orders = orderRepository.getOrdersByCustomerPaginated(getOrdersOfCustomerPojo);
        return orders.stream().map(GetOrdersOfCustomerDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @QueryEntityLogger
    public OrderDetailDTO getOrderDetail(Long id) {
        if (orderChecker.checkOrderExists(id)) {
            Order order = orderRepository.getById(id);
            return new OrderDetailDTO(order);
        }
        return new OrderDetailDTO();
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
    public List<OrderDetailDTO> getOrdersByDate(LocalDate startDate, LocalDate endDate, int pageNum, int pageSize) {
        GetOrdersByDatePojo getOrdersByDatePojo = new GetOrdersByDatePojo(startDate, endDate, pageNum, pageSize);
        List<Order> orders = orderRepository.getByDate(getOrdersByDatePojo);
        return orders.stream().map(OrderDetailDTO::new).collect(Collectors.toList());
    }

    private void setOrderCost(Order order) {
        Book book = bookService.getBookById(order.getBook().getId());
        double cost = order.getNumOfBooks() * book.getCost();
        order.setOrderCost(cost);
    }
}
