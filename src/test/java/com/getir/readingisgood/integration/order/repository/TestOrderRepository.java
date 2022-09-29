package com.getir.readingisgood.integration.order.repository;

import com.getir.readingisgood.common.AbstractIntegrationTest;
import com.getir.readingisgood.common.TestEntityBuilder;
import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.repository.OrderRepository;
import com.getir.readingisgood.order.service.pojo.GetOrdersByDatePojo;
import com.getir.readingisgood.order.service.pojo.GetOrdersOfCustomerPojo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class TestOrderRepository extends AbstractIntegrationTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testGetOrdersByCustomerPaginated() {
        GetOrdersOfCustomerPojo getOrdersOfCustomerPojo = TestEntityBuilder.createGetOrdersOfCustomersPojo(10000L);

        List<Order> orderList = orderRepository.getOrdersByCustomerPaginated(getOrdersOfCustomerPojo);

        Assertions.assertEquals(orderList.size(), 2);
    }

    @Test
    public void testGetOrdersByCustomer() {
        List<Order> orderList = orderRepository.getOrdersByCustomer(10001L);

        Assertions.assertEquals(orderList.size(), 2);
    }

    @Test
    public void testGetById() {
        Order order = orderRepository.getById(10003L);

        Assertions.assertNotNull(order);
        Assertions.assertEquals(order.getNumOfBooks(), 5L);
        Assertions.assertEquals(order.getOrderCost(), 75.);
    }

    @Test
    public void testAdd() {
        Order order = TestEntityBuilder.createOrder(null, 10000L, 10000L, 10000L);

        orderRepository.add(order);

        List<Order> orderList = orderRepository.getOrdersByCustomer(10000L);
        Assertions.assertEquals(orderList.size(), 3);
    }

    @Test
    public void testGetByDate() {
        GetOrdersByDatePojo getOrdersByDatePojo = TestEntityBuilder.createGetOrdersByDatePojo(LocalDate.of(2022, 9, 28), LocalDate.of(2022, 9, 28));

        List<Order> orderList = orderRepository.getByDate(getOrdersByDatePojo);

        Assertions.assertEquals(orderList.size(), 4);
    }
}
