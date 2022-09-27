package com.getir.readingisgood.customer.controller;

import com.getir.readingisgood.customer.controller.dto.AddCustomerDTO;
import com.getir.readingisgood.customer.controller.dto.GetOrdersOfCustomerDTO;
import com.getir.readingisgood.customer.service.CustomerService;
import com.getir.readingisgood.order.service.OrderService;
import com.getir.readingisgood.order.service.pojo.GetOrdersOfCustomersPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private final CustomerService customerService;

    private final OrderService orderService;

    @PostMapping(value = "/add")
    public void add(@Valid @RequestBody AddCustomerDTO addCustomerDTO) {
        customerService.add(addCustomerDTO.convertToDomainObject());
    }

    @GetMapping(value = "/getOrders")
    public List<GetOrdersOfCustomerDTO> getOrders(@RequestParam("customerId") @Min(1) Long customerId, @RequestParam("pageNum") @Min(0) int pageNum, @RequestParam("pageSize") @Min(1) int pageSize) {
        GetOrdersOfCustomersPojo getOrdersOfCustomersPojo = new GetOrdersOfCustomersPojo(customerId, pageNum, pageSize);
        return orderService.getOrdersOfCustomer(getOrdersOfCustomersPojo).stream().map(GetOrdersOfCustomerDTO::new).collect(Collectors.toList());
    }
}
