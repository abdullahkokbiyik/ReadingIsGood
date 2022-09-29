package com.getir.readingisgood.customer.service.checker;

import com.getir.readingisgood.common.context.MessageContext;
import com.getir.readingisgood.customer.entity.Customer;
import com.getir.readingisgood.customer.repository.CustomerRepository;
import com.getir.readingisgood.customer.service.message.CustomerMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerChecker {

    private final CustomerRepository customerRepository;
    private final MessageContext messageContext;

    public boolean checkCustomerExists(Long customerId) {
        Customer customer = customerRepository.getCustomerById(customerId);
        if (customer == null) {
            messageContext.addErrorMessage(CustomerMessages.ERROR_CUSTOMER_DOES_NOT_EXIST, "id", customerId);
            return false;
        }
        return true;
    }

    public boolean checkCustomerExists(Customer customer, String email) {
        if (customer == null) {
            messageContext.addErrorMessage(CustomerMessages.ERROR_CUSTOMER_DOES_NOT_EXIST, "email", email);
            return false;
        }
        return true;
    }
}
