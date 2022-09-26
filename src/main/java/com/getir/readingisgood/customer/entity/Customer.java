package com.getir.readingisgood.customer.entity;

import com.getir.readingisgood.common.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer extends AbstractEntity {

    @Column(name = "name")
    private String name;
}
