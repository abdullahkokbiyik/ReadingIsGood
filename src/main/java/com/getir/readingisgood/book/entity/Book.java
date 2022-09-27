package com.getir.readingisgood.book.entity;

import com.getir.readingisgood.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "stockAmount")
    private Long stockAmount;

    @Column(name = "cost")
    private Double cost;

    public Book(Long id) {
        super.setId(id);
    }

    public Book(Long id, Long stockAmount) {
        super.setId(id);
        this.stockAmount = stockAmount;
    }

    @Override
    public String toString() {
        return "{ Id: " + this.getId() + ", Name: " + this.name + ", Stock Amount: " + this.stockAmount + ", Cost: " + this.cost + " }";
    }
}
