package com.getir.readingisgood.book.service;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.repository.BookRepository;
import com.getir.readingisgood.book.service.checker.BookChecker;
import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookChecker bookChecker;

    private final RedissonClient client = Redisson.create();

    @Transactional
    public void add(Book book) {
        bookRepository.add(book);
    }

    @Transactional
    public void decreaseStockAmount(Long bookId, Long amount) {
        Book book = bookRepository.getById(bookId);
        if (bookChecker.checkValidForUpdatingStock(book, bookId, amount)) {
            book.setStockAmount(book.getStockAmount() - amount);
            bookRepository.update(book);
        }
    }

    @Transactional
    public void updateStockAmount(Book book) {
        Book bookDB = bookRepository.getById(book.getId());
        if (bookChecker.checkValidForUpdatingStock(bookDB, book.getId(), 0L)) {
            bookDB.setStockAmount(book.getStockAmount());
            bookRepository.update(bookDB);
        }
    }

    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        return bookRepository.getById(id);
    }
}
