package com.getir.readingisgood.order.controller.dto;

import com.getir.readingisgood.common.dto.ClientToServerDTO;
import com.getir.readingisgood.order.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class AddOrderDTO implements ClientToServerDTO<List<Order>> {

    private Long customerId;
    private List<BookOrderDTO> bookOrders;

    private LocalDateTime orderTime;

    @Override
    public List<Order> convertToDomainObject() {
        return bookOrders.stream().map(bookOrder -> new Order(customerId, bookOrder, orderTime)).toList();
    }
}
