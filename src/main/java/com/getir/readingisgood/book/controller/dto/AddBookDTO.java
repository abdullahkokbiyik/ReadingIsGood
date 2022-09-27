package com.getir.readingisgood.book.controller.dto;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.common.dto.ClientToServerDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class AddBookDTO implements ClientToServerDTO<Book> {

    @NotBlank
    @Size(max = 128)
    private String name;

    @NotNull
    @Min(1)
    private Long stockAmount;

    @NotNull
    @Positive
    private Double cost;

    @Override
    public Book convertToDomainObject() {
        return new Book(name, stockAmount, cost);
    }
}
