package com.getir.readingisgood.order.controller;

import com.getir.readingisgood.order.controller.dto.AddOrderDTO;
import com.getir.readingisgood.order.controller.dto.OrderDetailDTO;
import com.getir.readingisgood.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
