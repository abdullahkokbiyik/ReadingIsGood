package com.getir.readingisgood.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "email", unique = true)
    private String email;

    public Customer(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{ Id: " + this.getId() + ", Name: " + this.customerName + ", E-Mail: " + this.email + " }";
    }
}
