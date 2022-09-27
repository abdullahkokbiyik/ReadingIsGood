package com.getir.readingisgood.order.controller;

import com.getir.readingisgood.order.controller.dto.AddOrderDTO;
import com.getir.readingisgood.order.controller.dto.OrderDetailDTO;
import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/add")
    public void addOrder(@RequestBody AddOrderDTO addOrderDTO) {
        orderService.add(addOrderDTO.convertToDomainObject());
    }

    @GetMapping(value = "/getOrderDetail")
    public OrderDetailDTO getOrderDetail(@RequestParam("orderId") Long orderId) {
        return new OrderDetailDTO(orderService.getOrderById(orderId));
    }

    @GetMapping(value = "/getOrdersByDate")
    public List<OrderDetailDTO> getOrdersByDate(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        List<Order> orders = orderService.getOrdersByDate(startDate, endDate);
        return orders.stream().map(OrderDetailDTO::new).collect(Collectors.toList());
    }
}
