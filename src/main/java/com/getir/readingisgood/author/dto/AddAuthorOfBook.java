package com.getir.readingisgood.author.dto;

import com.getir.readingisgood.author.entity.Author;
import com.getir.readingisgood.common.dto.ClientToServerDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AddAuthorOfBook implements ClientToServerDTO<Author> {

    @NotBlank
    @Size(max = 200)
    private String name;

    @NotBlank
    @Size(max = 200)
    private String surname;

    @NotBlank
    @Size(max = 50)
    private String nationality;

    @NotBlank
    @Size(max = 38)
    private String uniqueIndex;

    @Override
    public Author convertToDomainObject() {
        return new Author(null, name, surname, nationality, uniqueIndex);
    }
}
