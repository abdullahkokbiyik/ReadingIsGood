package com.getir.readingisgood.order.repository;

import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.service.pojo.GetOrdersOfCustomersPojo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Order> getOrdersByCustomerId(GetOrdersOfCustomersPojo getOrdersOfCustomersPojo) {
        TypedQuery<Order> query = entityManager.createQuery("select o from Order o where o.customer.id = :customerId", Order.class);
        query.setParameter("customerId", getOrdersOfCustomersPojo.getCustomerId());
        query.setFirstResult(getOrdersOfCustomersPojo.getPageNum() * getOrdersOfCustomersPojo.getPageSize());
        query.setMaxResults(getOrdersOfCustomersPojo.getPageSize());
        return query.getResultList();
    }

    public Order getById(Long id) {
        return entityManager.find(Order.class, id);
    }

    public void add(Order order) {
        entityManager.persist(order);
    }
}
