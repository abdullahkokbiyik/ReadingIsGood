package com.getir.readingisgood.customer.controller;

import com.getir.readingisgood.customer.controller.dto.AddCustomerDTO;
import com.getir.readingisgood.customer.controller.dto.GetOrdersOfCustomerDTO;
import com.getir.readingisgood.customer.service.CustomerService;
import com.getir.readingisgood.order.service.OrderService;
import com.getir.readingisgood.order.service.pojo.GetOrdersOfCustomersPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private final OrderService orderService;

    @PostMapping(value = "/add")
    public void add(@RequestBody AddCustomerDTO addCustomerDTO) {
        customerService.add(addCustomerDTO.convertToDomainObject());
    }

    @GetMapping(value = "/getOrders")
    public List<GetOrdersOfCustomerDTO> getOrders(@RequestParam("customerId") Long customerId, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        GetOrdersOfCustomersPojo getOrdersOfCustomersPojo = new GetOrdersOfCustomersPojo(customerId, pageNum, pageSize);
        return orderService.getOrdersOfCustomer(getOrdersOfCustomersPojo).stream().map(GetOrdersOfCustomerDTO::new).collect(Collectors.toList());
    }
}
