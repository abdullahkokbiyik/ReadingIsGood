package com.getir.readingisgood.customer.service;

import com.getir.readingisgood.common.annotations.SaveEntityLogger;
import com.getir.readingisgood.customer.entity.Customer;
import com.getir.readingisgood.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    @SaveEntityLogger
    public void add(Customer customer) {
        customerRepository.add(customer);
    }
}
