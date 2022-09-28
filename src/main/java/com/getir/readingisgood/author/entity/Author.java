package com.getir.readingisgood.author.entity;

import com.getir.readingisgood.common.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "author")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Author extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "nationality", nullable = false)
    private String nationality;

    @Column(name = "uid", unique = true, nullable = false)
    private String uniqueIndex;

    @Override
    public String toString() {
        return "{ Name: " + this.name + ", Surname: " + this.surname + ", Nationality: " + this.nationality + ", Unique Index: " + this.uniqueIndex + " }";
    }

    public void copyFromAnotherObject(Author author) {
        this.name = author.getName();
        this.nationality = author.getNationality();
        this.surname = author.getSurname();
        this.uniqueIndex = author.getUniqueIndex();
        this.setId(author.getId());
    }
}
