package com.getir.readingisgood.customer.repository;

import com.getir.readingisgood.customer.entity.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

    public Customer getByEmail(String email) {
        TypedQuery<Customer> query = entityManager.createQuery("select c from Customer c where c.email = :email ", Customer.class);
        query.setParameter("email", email);
        return query.getResultList().stream().findFirst().orElse(null);
    }
}
