package com.getir.readingisgood.book.controller.dto;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.common.dto.ClientToServerDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateStockDTO implements ClientToServerDTO<Book> {

    @NotNull
    @Min(1)
    private Long bookId;

    @NotNull
    @Min(1)
    private Long newStockAmount;

    @Override
    public Book convertToDomainObject() {
        return new Book(bookId, newStockAmount);
    }
}
