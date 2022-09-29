package com.getir.readingisgood.customer.service;

import com.getir.readingisgood.common.annotations.QueryEntityLogger;
import com.getir.readingisgood.common.annotations.SaveEntityLogger;
import com.getir.readingisgood.customer.controller.dto.GetCustomerDetailDTO;
import com.getir.readingisgood.customer.entity.Customer;
import com.getir.readingisgood.customer.repository.CustomerRepository;
import com.getir.readingisgood.customer.service.checker.CustomerChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerChecker customerChecker;

    @Transactional
    @SaveEntityLogger
    public void add(Customer customer) {
        customerRepository.add(customer);
    }

    @Transactional
    @QueryEntityLogger
    public GetCustomerDetailDTO getCustomerByEmail(String email) {
        Customer customer = customerRepository.getByEmail(email);
        if (customerChecker.checkCustomerExists(customer, email)) {
            return new GetCustomerDetailDTO(customer);
        }
        return new GetCustomerDetailDTO();
    }
}
