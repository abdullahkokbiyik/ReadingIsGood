package com.getir.readingisgood.integration.customer.repository;

import com.getir.readingisgood.common.AbstractIntegrationTest;
import com.getir.readingisgood.common.TestEntityBuilder;
import com.getir.readingisgood.customer.entity.Customer;
import com.getir.readingisgood.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestCustomerRepository extends AbstractIntegrationTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testAdd() {
        Customer customer = TestEntityBuilder.createCustomer(null);

        customerRepository.add(customer);

        Customer customerDB = customerRepository.getByEmail(customer.getEmail());
        Assertions.assertNotNull(customerDB);
    }

    @Test
    public void testGetById() {
        Customer customer = customerRepository.getCustomerById(10000L);

        Assertions.assertNotNull(customer);
        Assertions.assertEquals(customer.getEmail(), "testcustomer@gmail.com");
    }

    @Test
    public void testGetByEmail() {
        Customer customer = customerRepository.getByEmail("testcustomer@gmail.com");

        Assertions.assertNotNull(customer);
        Assertions.assertEquals(customer.getId(), Long.valueOf(10000));
    }
}
