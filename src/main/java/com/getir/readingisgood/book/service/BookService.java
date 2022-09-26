package com.getir.readingisgood.book.service;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.repository.BookRepository;
import com.getir.readingisgood.book.service.checker.BookStockAmountChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookStockAmountChecker bookStockAmountChecker;

    @Transactional
    public void add(Book book) {
        bookRepository.add(book);
    }

    @Transactional
    public void decreaseStockAmount(Long bookId, Long amount) {
        Book book = bookRepository.getById(bookId);
        book.setStockAmount(book.getStockAmount() - amount);
        if (bookStockAmountChecker.checkStockAmountValid(book)) {
            bookRepository.update(book);
        }
    }

    @Transactional
    public void updateStockAmount(Book book) {
        if (bookStockAmountChecker.checkStockAmountValid(book)) {
            Book bookDB = bookRepository.getById(book.getId());
            bookDB.setStockAmount(book.getStockAmount());
            bookRepository.update(bookDB);
        }
    }

    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        return bookRepository.getById(id);
    }
}
