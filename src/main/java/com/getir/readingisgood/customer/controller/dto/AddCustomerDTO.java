package com.getir.readingisgood.customer.controller.dto;

import com.getir.readingisgood.common.dto.ClientToServerDTO;
import com.getir.readingisgood.customer.entity.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AddCustomerDTO implements ClientToServerDTO<Customer> {

    @NotBlank
    @Size(max = 128)
    private String name;

    @NotBlank
    @Size(max = 200)
    private String email;

    @Override
    public Customer convertToDomainObject() {
        return new Customer(null, name, email);
    }
}
