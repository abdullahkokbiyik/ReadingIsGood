package com.getir.readingisgood.customer.controller.dto;

import com.getir.readingisgood.customer.entity.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetCustomerDetailDTO {
    private Long id;
    private String name;
    private String email;

    public GetCustomerDetailDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getCustomerName();
        this.email = customer.getEmail();
    }
}
