package com.getir.readingisgood.unit.order.service;

import com.getir.readingisgood.book.service.BookService;
import com.getir.readingisgood.common.AbstractUnitTest;
import com.getir.readingisgood.common.TestEntityBuilder;
import com.getir.readingisgood.customer.controller.dto.GetOrdersOfCustomerDTO;
import com.getir.readingisgood.order.controller.dto.OrderDetailDTO;
import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.repository.OrderRepository;
import com.getir.readingisgood.order.service.OrderService;
import com.getir.readingisgood.order.service.checker.OrderChecker;
import com.getir.readingisgood.order.service.pojo.GetOrdersByDatePojo;
import com.getir.readingisgood.order.service.pojo.GetOrdersOfCustomerPojo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class TestOrderService extends AbstractUnitTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private BookService bookService;

    @Mock
    private OrderChecker orderChecker;

    @Test
    public void testGetOrdersOfCustomer() {
        Long customerId = 1L;
        Order order = TestEntityBuilder.createOrder(1L, 1L, 1L, customerId);
        List<Order> orders = Collections.singletonList(order);
        GetOrdersOfCustomerPojo getOrdersOfCustomerPojo = TestEntityBuilder.createGetOrdersOfCustomersPojo(customerId);

        Mockito.when(orderRepository.getOrdersByCustomerPaginated(ArgumentMatchers.argThat(getOrdersOfCustomersPojoMatcher(getOrdersOfCustomerPojo)))).thenReturn(orders);

        List<GetOrdersOfCustomerDTO> ordersOfCustomerList = orderService.getOrdersOfCustomer(customerId, 0, 10);

        Assertions.assertEquals(orders.size(), ordersOfCustomerList.size());
        GetOrdersOfCustomerDTO ordersOfCustomer = ordersOfCustomerList.get(0);
        Assertions.assertEquals(order.getOrderCost(), ordersOfCustomer.getOrderCost());
        Assertions.assertEquals(order.getCustomer().getEmail(), ordersOfCustomer.getCustomerEmail());
        Assertions.assertEquals(order.getBook().getBookName(), ordersOfCustomer.getBookName());

        Mockito.verify(orderRepository).getOrdersByCustomerPaginated(ArgumentMatchers.argThat(getOrdersOfCustomersPojoMatcher(getOrdersOfCustomerPojo)));
    }

    @Test
    public void testGetOrderDetail() {
        Long orderId = 1L;
        Order order = TestEntityBuilder.createOrder(orderId, 1L, 1L, 1L);

        Mockito.when(orderChecker.checkOrderExists(orderId)).thenReturn(true);
        Mockito.when(orderRepository.getById(orderId)).thenReturn(order);

        OrderDetailDTO orderDetailDTO = orderService.getOrderDetail(orderId);

        Assertions.assertEquals(order.getOrderCost(), orderDetailDTO.getOrderCost());
        Assertions.assertEquals(order.getCustomer().getEmail(), orderDetailDTO.getCustomerEmail());
        Assertions.assertEquals(order.getBook().getBookName(), orderDetailDTO.getBookName());

        Mockito.verify(orderChecker).checkOrderExists(orderId);
        Mockito.verify(orderRepository).getById(orderId);
    }

    @Test
    public void testAdd() {
        Long bookId = 1L;
        Long authorId = 1L;
        Long customerId = 1L;
        Order order = TestEntityBuilder.createOrder(null, bookId, authorId, customerId);
        List<Order> orders = Collections.singletonList(order);

        Mockito.when(orderChecker.checkForAddOrder(orders)).thenReturn(true);
        Mockito.when(bookService.getBookById(bookId)).thenReturn(orders.get(0).getBook());

        orderService.add(orders);

        Mockito.verify(orderChecker).checkForAddOrder(orders);
        Mockito.verify(bookService).getBookById(bookId);
        Mockito.verify(orderRepository).add(order);
        Mockito.verify(bookService).decreaseStockAmount(order.getBook().getId(), order.getNumOfBooks());
    }

    @Test
    public void testGetOrdersByDate() {
        LocalDate now = LocalDate.now();
        List<Order> orders = Collections.singletonList(TestEntityBuilder.createOrder(1L, 1L, 1L, 1L));
        GetOrdersByDatePojo getOrdersByDatePojo = TestEntityBuilder.createGetOrdersByDatePojo(now, now);

        Mockito.when(orderRepository.getByDate(ArgumentMatchers.argThat(getOrdersByDatePojoMatcher(getOrdersByDatePojo)))).thenReturn(orders);

        List<OrderDetailDTO> orderDetails = orderService.getOrdersByDate(now, now, 0, 10);

        Assertions.assertEquals(orders.size(), orderDetails.size());
        Order order = orders.get(0);
        OrderDetailDTO orderDetail = orderDetails.get(0);
        Assertions.assertEquals(order.getOrderCost(), orderDetail.getOrderCost());
        Assertions.assertEquals(order.getCustomer().getEmail(), orderDetail.getCustomerEmail());
        Assertions.assertEquals(order.getBook().getBookName(), orderDetail.getBookName());

        Mockito.verify(orderRepository).getByDate(ArgumentMatchers.argThat(getOrdersByDatePojoMatcher(getOrdersByDatePojo)));
    }

    private ArgumentMatcher<GetOrdersOfCustomerPojo> getOrdersOfCustomersPojoMatcher(final GetOrdersOfCustomerPojo getOrdersOfCustomerPojo) {
        return matcher -> {
            Assertions.assertEquals(matcher.getCustomerId(), getOrdersOfCustomerPojo.getCustomerId());
            Assertions.assertEquals(matcher.getPageNum(), getOrdersOfCustomerPojo.getPageNum());
            Assertions.assertEquals(matcher.getPageSize(), getOrdersOfCustomerPojo.getPageSize());
            return true;
        };
    }

    private ArgumentMatcher<GetOrdersByDatePojo> getOrdersByDatePojoMatcher(final GetOrdersByDatePojo getOrdersByDatePojo) {
        return matcher -> {
            Assertions.assertEquals(matcher.getEndDate(), getOrdersByDatePojo.getEndDate());
            Assertions.assertEquals(matcher.getStartDate(), getOrdersByDatePojo.getStartDate());
            Assertions.assertEquals(matcher.getPageSize(), getOrdersByDatePojo.getPageSize());
            Assertions.assertEquals(matcher.getPageNum(), getOrdersByDatePojo.getPageNum());
            return true;
        };
    }
}
