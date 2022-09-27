package com.getir.readingisgood.order.controller;

import com.getir.readingisgood.order.controller.dto.AddOrderDTO;
import com.getir.readingisgood.order.controller.dto.OrderDetailDTO;
import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.service.OrderService;
import com.getir.readingisgood.order.service.message.OrderMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/add")
    public ResponseEntity<String> addOrder(@Valid @RequestBody AddOrderDTO addOrderDTO) {
        orderService.add(addOrderDTO.convertToDomainObject());
        return new ResponseEntity<>(OrderMessages.ADD_ORDER_SUCCESS_MESSAGE, HttpStatus.OK);
    }

    @GetMapping(value = "/getOrderDetail")
    public OrderDetailDTO getOrderDetail(@RequestParam("orderId") @Min(1) Long orderId) {
        return new OrderDetailDTO(orderService.getOrderById(orderId));
    }

    @GetMapping(value = "/getOrdersByDate")
    public List<OrderDetailDTO> getOrdersByDate(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        List<Order> orders = orderService.getOrdersByDate(startDate, endDate);
        return orders.stream().map(OrderDetailDTO::new).collect(Collectors.toList());
    }
}
