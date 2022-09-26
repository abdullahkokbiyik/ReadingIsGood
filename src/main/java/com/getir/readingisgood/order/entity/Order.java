package com.getir.readingisgood.order.entity;

import com.getir.readingisgood.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customer_order")
public class Order extends AbstractEntity {
}
