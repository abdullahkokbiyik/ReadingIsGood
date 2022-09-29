package com.getir.readingisgood.author.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "author")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "uid", unique = true)
    private String uniqueIndex;

    @Override
    public String toString() {
        return "{ Name: " + this.authorName + ", Surname: " + this.surname + ", Nationality: " + this.nationality + ", Unique Index: " + this.uniqueIndex + " }";
    }

    public void copyFromAnotherObject(Author author) {
        this.authorName = author.getAuthorName();
        this.nationality = author.getNationality();
        this.surname = author.getSurname();
        this.uniqueIndex = author.getUniqueIndex();
        this.id = author.getId();
    }
}
