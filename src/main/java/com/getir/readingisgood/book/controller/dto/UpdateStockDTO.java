package com.getir.readingisgood.book.controller.dto;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.common.dto.ClientToServerDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class UpdateStockDTO implements ClientToServerDTO<Book> {

    @NotNull
    @Positive
    private Long bookId;

    @NotNull
    @Positive
    private Long newStockAmount;

    @Override
    public Book convertToDomainObject() {
        return new Book(bookId, newStockAmount);
    }
}
