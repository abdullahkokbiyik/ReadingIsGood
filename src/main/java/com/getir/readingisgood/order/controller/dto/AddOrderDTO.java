package com.getir.readingisgood.order.controller.dto;

import com.getir.readingisgood.common.dto.ClientToServerDTO;
import com.getir.readingisgood.order.entity.Order;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class AddOrderDTO implements ClientToServerDTO<List<Order>> {

    @NotNull
    @Min(1)
    private Long customerId;

    @NotEmpty
    private List<@Valid BookOrderDTO> bookOrders;

    @NotNull
    private LocalDateTime orderTime;

    @Override
    public List<Order> convertToDomainObject() {
        return bookOrders.stream().map(bookOrder -> new Order(customerId, bookOrder, orderTime)).toList();
    }
}
