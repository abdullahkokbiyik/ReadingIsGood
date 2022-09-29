package com.getir.readingisgood.customer.controller;

import com.getir.readingisgood.customer.controller.dto.AddCustomerDTO;
import com.getir.readingisgood.customer.controller.dto.GetOrdersOfCustomerDTO;
import com.getir.readingisgood.customer.service.CustomerService;
import com.getir.readingisgood.customer.service.message.CustomerMessages;
import com.getir.readingisgood.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private final CustomerService customerService;

    private final OrderService orderService;

    @PostMapping(value = "/add")
    public ResponseEntity<String> add(@Valid @RequestBody AddCustomerDTO addCustomerDTO) {
        customerService.add(addCustomerDTO.convertToDomainObject());
        return new ResponseEntity<>(CustomerMessages.ADD_CUSTOMER_SUCCESS_MESSAGE, HttpStatus.OK);
    }

    @GetMapping(value = "/getOrdersOfCustomer")
    public List<GetOrdersOfCustomerDTO> getOrdersOfCustomer(@RequestParam("customerId") @Min(1) Long customerId, @RequestParam("pageNum") @Min(0) int pageNum, @RequestParam("pageSize") @Min(1) int pageSize) {
        return orderService.getOrdersOfCustomer(customerId, pageNum, pageSize);
    }
}
