package com.getir.readingisgood.unit.customer.checker;

import com.getir.readingisgood.common.AbstractUnitTest;
import com.getir.readingisgood.common.TestEntityBuilder;
import com.getir.readingisgood.common.context.MessageContext;
import com.getir.readingisgood.customer.entity.Customer;
import com.getir.readingisgood.customer.repository.CustomerRepository;
import com.getir.readingisgood.customer.service.checker.CustomerChecker;
import com.getir.readingisgood.customer.service.message.CustomerMessages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TestCustomerChecker extends AbstractUnitTest {

    @InjectMocks
    private CustomerChecker customerChecker;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private MessageContext messageContext;

    @Test
    public void testCheckCustomerExists() {
        Long customerId = 1L;
        Customer customer = TestEntityBuilder.createCustomer(customerId);

        Mockito.when(customerRepository.getCustomerById(customerId)).thenReturn(customer);

        Assertions.assertTrue(customerChecker.checkCustomerExists(customerId));

        Mockito.verify(customerRepository).getCustomerById(customerId);
    }

    @Test
    public void testCheckCustomerNotExists() {
        Long customerId = 1L;

        Mockito.when(customerRepository.getCustomerById(customerId)).thenReturn(null);

        Assertions.assertFalse(customerChecker.checkCustomerExists(customerId));

        Mockito.verify(customerRepository).getCustomerById(customerId);
        Mockito.verify(messageContext).addErrorMessage(CustomerMessages.ERROR_CUSTOMER_DOES_NOT_EXIST, customerId);
    }
}
