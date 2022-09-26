package com.getir.readingisgood.customer.controller.dto;

import com.getir.readingisgood.common.dto.ClientToServerDTO;
import com.getir.readingisgood.customer.entity.Customer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCustomerDTO implements ClientToServerDTO<Customer> {

    private String name;
    private String email;

    @Override
    public Customer convertToDomainObject() {
        return new Customer(name, email);
    }
}
