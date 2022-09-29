package com.getir.readingisgood.book.entity;

import com.getir.readingisgood.author.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "stock_amount")
    private Long stockAmount;

    @Column(name = "cost")
    private Double cost;

    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Author.class)
    private Author author;

    @Column(name = "uid", unique = true)
    private String uniqueIndex;

    public Book(Long id) {
        this.id = id;
    }

    public Book(Long id, Long stockAmount) {
        this.id = id;
        this.stockAmount = stockAmount;
    }

    @Override
    public String toString() {
        return "{ Id: " + this.getId() + ", Name: " + this.bookName + ", Stock Amount: " + this.stockAmount + ", Cost: " + this.cost +
                ", Author: " + this.author + ", Unique Index: " + this.uniqueIndex + " }";
    }
}
