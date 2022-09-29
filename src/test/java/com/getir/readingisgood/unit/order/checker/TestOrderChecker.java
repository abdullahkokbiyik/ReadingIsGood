package com.getir.readingisgood.unit.order.checker;

import com.getir.readingisgood.book.service.checker.BookChecker;
import com.getir.readingisgood.common.AbstractUnitTest;
import com.getir.readingisgood.common.TestEntityBuilder;
import com.getir.readingisgood.common.context.MessageContext;
import com.getir.readingisgood.customer.service.checker.CustomerChecker;
import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.repository.OrderRepository;
import com.getir.readingisgood.order.service.checker.OrderChecker;
import com.getir.readingisgood.order.service.message.OrderMessages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

public class TestOrderChecker extends AbstractUnitTest {

    @InjectMocks
    private OrderChecker orderChecker;

    @Mock
    private CustomerChecker customerChecker;

    @Mock
    private BookChecker bookChecker;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private MessageContext messageContext;

    @Test
    public void testCheckForAddOrder() {
        List<Order> orders = Collections.singletonList(TestEntityBuilder.createOrder(null, 1L, 1L, 1L));

        Mockito.when(customerChecker.checkCustomerExists(orders.get(0).getCustomer().getId())).thenReturn(true);
        orders.forEach(order -> Mockito.when(bookChecker.checkExists(order.getBook().getId())).thenReturn(true));

        Assertions.assertTrue(orderChecker.checkForAddOrder(orders));

        Mockito.verify(customerChecker).checkCustomerExists(orders.get(0).getCustomer().getId());
        orders.forEach(order -> Mockito.verify(bookChecker).checkExists(order.getBook().getId()));
    }

    @Test
    public void testCheckForAddOrderCustomerNotExists() {
        List<Order> orders = Collections.singletonList(TestEntityBuilder.createOrder(null, 1L, 1L, 1L));

        Mockito.when(customerChecker.checkCustomerExists(orders.get(0).getCustomer().getId())).thenReturn(false);

        Assertions.assertFalse(orderChecker.checkForAddOrder(orders));

        Mockito.verify(customerChecker).checkCustomerExists(orders.get(0).getCustomer().getId());
    }

    @Test
    public void testCheckForAddOrderBookNotExists() {
        List<Order> orders = Collections.singletonList(TestEntityBuilder.createOrder(null, 1L, 1L, 1L));

        Mockito.when(customerChecker.checkCustomerExists(orders.get(0).getCustomer().getId())).thenReturn(true);
        orders.forEach(order -> Mockito.when(bookChecker.checkExists(order.getBook().getId())).thenReturn(false));

        Assertions.assertFalse(orderChecker.checkForAddOrder(orders));

        Mockito.verify(customerChecker).checkCustomerExists(orders.get(0).getCustomer().getId());
        orders.forEach(order -> Mockito.verify(bookChecker).checkExists(order.getBook().getId()));
    }

    @Test
    public void testCheckOrderExists() {
        Long orderId = 1L;
        Order order = TestEntityBuilder.createOrder(orderId, 1L, 1L, 1L);

        Mockito.when(orderRepository.getById(orderId)).thenReturn(order);

        Assertions.assertTrue(orderChecker.checkOrderExists(orderId));

        Mockito.verify(orderRepository).getById(orderId);
    }

    @Test
    public void testCheckOrderNotExists() {
        Long orderId = 1L;

        Mockito.when(orderRepository.getById(orderId)).thenReturn(null);

        Assertions.assertFalse(orderChecker.checkOrderExists(orderId));

        Mockito.verify(orderRepository).getById(orderId);
        Mockito.verify(messageContext).addErrorMessage(OrderMessages.ERROR_ORDER_DOES_NOT_EXIST, orderId);
    }
}
