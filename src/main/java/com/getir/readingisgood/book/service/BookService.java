package com.getir.readingisgood.book.service;

import com.getir.readingisgood.author.service.AuthorService;
import com.getir.readingisgood.book.controller.dto.GetBookDetailDTO;
import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.repository.BookRepository;
import com.getir.readingisgood.book.service.checker.BookChecker;
import com.getir.readingisgood.common.annotations.QueryEntityLogger;
import com.getir.readingisgood.common.annotations.SaveEntityLogger;
import com.getir.readingisgood.common.annotations.UpdateEntityLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookChecker bookChecker;
    private final AuthorService authorService;

    @Transactional
    @SaveEntityLogger
    public void add(Book book) {
        authorService.getIfExistsOrAdd(book.getAuthor());
        bookRepository.add(book);
    }

    @Transactional
    @UpdateEntityLogger
    public void decreaseStockAmount(Long bookId, Long amount) {
        Book book = bookRepository.getById(bookId);
        if (bookChecker.checkValidForUpdatingStock(book, bookId, amount)) {
            book.setStockAmount(book.getStockAmount() - amount);
            bookRepository.update(book);
        }
    }

    @Transactional
    @UpdateEntityLogger
    public void updateStockAmount(Book book) {
        Book bookDB = bookRepository.getById(book.getId());
        if (bookChecker.checkValidForUpdatingStock(bookDB, book.getId(), 0L)) {
            bookDB.setStockAmount(book.getStockAmount());
            bookRepository.update(bookDB);
        }
    }

    @Transactional(readOnly = true)
    @QueryEntityLogger
    public Book getBookById(Long id) {
        return bookRepository.getById(id);
    }

    @Transactional(readOnly = true)
    @QueryEntityLogger
    public GetBookDetailDTO getBookDetail(Long id) {
        if (bookChecker.checkExists(id)) {
            Book book = getBookById(id);
            return new GetBookDetailDTO(book);
        }
        return new GetBookDetailDTO();
    }
}
