package com.getir.readingisgood.customer.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Customer {

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
