package com.getir.readingisgood.customer.repository;

import com.getir.readingisgood.customer.entity.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void add(Customer customer) {
        entityManager.persist(customer);
    }

    public Customer getCustomerById(Long id) {
        return entityManager.find(Customer.class, id);
    }
}
