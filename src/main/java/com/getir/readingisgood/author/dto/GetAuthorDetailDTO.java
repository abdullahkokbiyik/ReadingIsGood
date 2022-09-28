package com.getir.readingisgood.author.dto;

import com.getir.readingisgood.author.entity.Author;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAuthorDetailDTO {
    private String name;
    private String surname;
    private String nationality;
    private String uniqueIndex;

    public GetAuthorDetailDTO(Author author) {
        this.name = author.getName();
        this.surname = author.getSurname();
        this.nationality = author.getNationality();
        this.uniqueIndex = author.getUniqueIndex();
    }
}
