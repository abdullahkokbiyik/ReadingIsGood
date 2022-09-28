package com.getir.readingisgood.book.entity;

import com.getir.readingisgood.author.entity.Author;
import com.getir.readingisgood.common.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "stockAmount", nullable = false)
    private Long stockAmount;

    @Column(name = "cost", nullable = false)
    private Double cost;

    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;

    @Column(name = "uid", unique = true, nullable = false)
    private String uniqueIndex;

    public Book(Long id) {
        super.setId(id);
    }

    public Book(Long id, Long stockAmount) {
        super.setId(id);
        this.stockAmount = stockAmount;
    }

    @Override
    public String toString() {
        return "{ Id: " + this.getId() + ", Name: " + this.name + ", Stock Amount: " + this.stockAmount + ", Cost: " + this.cost +
                ", Author: " + this.author + ", Unique Index: " + this.uniqueIndex + " }";
    }
}
