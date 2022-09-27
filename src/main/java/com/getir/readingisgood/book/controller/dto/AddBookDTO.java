package com.getir.readingisgood.book.controller.dto;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.common.dto.ClientToServerDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBookDTO implements ClientToServerDTO<Book> {

    private String name;
    private Long stockAmount;

    private Double cost;

    @Override
    public Book convertToDomainObject() {
        return new Book(name, stockAmount, cost);
    }
}
