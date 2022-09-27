package com.getir.readingisgood.book.entity;

import com.getir.readingisgood.common.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public boolean isStockAmountValid() {
        return stockAmount >= 0;
    }
}
