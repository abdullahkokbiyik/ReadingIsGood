package com.getir.readingisgood.book.controller.dto;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.common.dto.ClientToServerDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStockDTO implements ClientToServerDTO<Book> {
    private Long bookId;
    private Long newStockAmount;

    @Override
    public Book convertToDomainObject() {
        return new Book(bookId, newStockAmount);
    }
}
