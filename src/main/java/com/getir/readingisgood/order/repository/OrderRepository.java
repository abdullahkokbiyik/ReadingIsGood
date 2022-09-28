package com.getir.readingisgood.order.repository;

import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.service.pojo.GetOrdersByDatePojo;
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

    public List<Order> getOrdersByCustomer(GetOrdersOfCustomersPojo getOrdersOfCustomersPojo) {
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

    public List<Order> getByDate(GetOrdersByDatePojo getOrdersByDatePojo) {
        TypedQuery<Order> query = entityManager.createQuery("select o from Order o where o.orderDate between :startDate and :endDate ", Order.class);
        query.setParameter("startDate", getOrdersByDatePojo.getStartDate());
        query.setParameter("endDate", getOrdersByDatePojo.getEndDate());
        query.setFirstResult(getOrdersByDatePojo.getPageNum() * getOrdersByDatePojo.getPageSize());
        query.setMaxResults(getOrdersByDatePojo.getPageSize());
        return query.getResultList();
    }
}
