package com.getir.readingisgood.customer.entity;

import com.getir.readingisgood.common.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends AbstractEntity {

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "email", unique = true)
    private String email;

    public Customer(Long id) {
        super.setId(id);
    }

    @Override
    public String toString() {
        return "{ Id: " + this.getId() + ", Name: " + this.customerName + ", E-Mail: " + this.email + " }";
    }
}
