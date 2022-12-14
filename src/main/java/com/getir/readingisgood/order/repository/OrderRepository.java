package com.getir.readingisgood.order.repository;

import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.service.pojo.GetOrdersByDatePojo;
import com.getir.readingisgood.order.service.pojo.GetOrdersOfCustomerPojo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Order> getOrdersByCustomerPaginated(GetOrdersOfCustomerPojo getOrdersOfCustomerPojo) {
        TypedQuery<Order> query = entityManager.createQuery("select o from Order o where o.customer.id = :customerId", Order.class);
        query.setParameter("customerId", getOrdersOfCustomerPojo.getCustomerId());
        query.setFirstResult(getOrdersOfCustomerPojo.getPageNum() * getOrdersOfCustomerPojo.getPageSize());
        query.setMaxResults(getOrdersOfCustomerPojo.getPageSize());
        return query.getResultList();
    }

    public List<Order> getOrdersByCustomer(Long customerId) {
        TypedQuery<Order> query = entityManager.createQuery("select o from Order o where o.customer.id = :customerId", Order.class);
        query.setParameter("customerId", customerId);
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
