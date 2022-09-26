package com.getir.readingisgood.book.service.checker;

import com.getir.readingisgood.book.entity.Book;
import org.springframework.stereotype.Service;

@Service
public class BookStockAmountChecker {

    public boolean checkStockAmountValid(Book book) {
        if (book.isStockAmountValid()) {
            return true;
        }
        throw new IllegalArgumentException("Out of stock");
    }
}
