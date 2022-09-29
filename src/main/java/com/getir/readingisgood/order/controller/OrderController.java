package com.getir.readingisgood.order.controller;

import com.getir.readingisgood.order.controller.dto.AddOrderDTO;
import com.getir.readingisgood.order.controller.dto.OrderDetailDTO;
import com.getir.readingisgood.order.service.OrderService;
import com.getir.readingisgood.order.service.message.OrderMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;

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
        return orderService.getOrderDetail(orderId);
    }

    @GetMapping(value = "/getOrdersByDate")
    public List<OrderDetailDTO> getOrdersByDate(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                @RequestParam("pageNum") @Min(0) int pageNum, @RequestParam("pageSize") @Min(1) int pageSize) {
        return orderService.getOrdersByDate(startDate, endDate, pageNum, pageSize);
    }
}
