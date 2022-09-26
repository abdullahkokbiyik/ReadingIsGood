package com.getir.readingisgood.book.controller.dto;

import com.getir.readingisgood.book.entity.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetBookDetailDTO {
    private Long id;
    private String name;
    private Long stockAmount;

    public GetBookDetailDTO(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.stockAmount = book.getStockAmount();
    }
}
