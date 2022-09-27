package com.getir.readingisgood.order.service.checker;

import com.getir.readingisgood.book.service.checker.BookChecker;
import com.getir.readingisgood.common.context.MessageContext;
import com.getir.readingisgood.customer.service.checker.CustomerChecker;
import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.repository.OrderRepository;
import com.getir.readingisgood.order.service.message.OrderMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderChecker {

    private final CustomerChecker customerChecker;
    private final BookChecker bookChecker;
    private final OrderRepository orderRepository;
    private final MessageContext messageContext;

    public boolean checkForAddOrder(List<Order> orders) {
        return customerChecker.checkCustomerExists(orders.get(0).getCustomer().getId())
                && orders.stream().allMatch(order -> bookChecker.checkExists(order.getBook().getId()));
    }

    public boolean checkOrderExists(Long orderId) {
        Order order = orderRepository.getById(orderId);
        if (order == null) {
            messageContext.addErrorMessage(OrderMessages.ERROR_ORDER_DOES_NOT_EXIST, orderId);
            return false;
        }
        return true;
    }
}
