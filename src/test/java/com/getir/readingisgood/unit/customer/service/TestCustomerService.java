package com.getir.readingisgood.unit.customer.service;

import com.getir.readingisgood.common.AbstractUnitTest;
import com.getir.readingisgood.common.TestEntityBuilder;
import com.getir.readingisgood.customer.controller.dto.GetCustomerDetailDTO;
import com.getir.readingisgood.customer.entity.Customer;
import com.getir.readingisgood.customer.repository.CustomerRepository;
import com.getir.readingisgood.customer.service.CustomerService;
import com.getir.readingisgood.customer.service.checker.CustomerChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TestCustomerService extends AbstractUnitTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerChecker customerChecker;

    @Test
    public void testGetCustomerByEmail() {
        Customer customer = TestEntityBuilder.createCustomer(1L);
        String email = customer.getEmail();

        Mockito.when(customerRepository.getByEmail(email)).thenReturn(customer);
        Mockito.when(customerChecker.checkCustomerExists(customer, email)).thenReturn(true);

        GetCustomerDetailDTO customerDetail = customerService.getCustomerByEmail(email);

        Assertions.assertEquals(customer.getEmail(), customerDetail.getEmail());
        Assertions.assertEquals(customer.getId(), customerDetail.getId());
        Assertions.assertEquals(customer.getCustomerName(), customerDetail.getName());

        Mockito.verify(customerRepository).getByEmail(email);
        Mockito.verify(customerChecker).checkCustomerExists(customer, email);
    }
}
