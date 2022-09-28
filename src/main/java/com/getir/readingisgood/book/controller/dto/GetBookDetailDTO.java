package com.getir.readingisgood.book.controller.dto;

import com.getir.readingisgood.author.dto.GetAuthorDetailDTO;
import com.getir.readingisgood.book.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetBookDetailDTO {
    private Long id;
    private String name;
    private Long stockAmount;
    private double cost;
    private GetAuthorDetailDTO author;
    private String uniqueIndex;

    public GetBookDetailDTO(Book book) {
        this.id = book.getId();
        this.name = book.getBookName();
        this.stockAmount = book.getStockAmount();
        this.cost = book.getCost();
        this.author = new GetAuthorDetailDTO(book.getAuthor());
        this.uniqueIndex = book.getUniqueIndex();
    }
}
