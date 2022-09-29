package com.getir.readingisgood.unit.book.service;

import com.getir.readingisgood.book.controller.dto.GetBookDetailDTO;
import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.repository.BookRepository;
import com.getir.readingisgood.book.service.BookService;
import com.getir.readingisgood.book.service.checker.BookChecker;
import com.getir.readingisgood.common.AbstractUnitTest;
import com.getir.readingisgood.common.TestEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TestBookService extends AbstractUnitTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookChecker bookChecker;

    @Test
    public void testDecreaseStockAmount() {
        Long bookId = 1L;
        Long amount = 5L;
        Book book = TestEntityBuilder.createBook(bookId, 1L);

        Mockito.when(bookRepository.getById(bookId)).thenReturn(book);
        Mockito.when(bookChecker.checkValidForUpdatingStock(book, bookId, amount)).thenReturn(true);

        bookService.decreaseStockAmount(bookId, amount);

        Mockito.verify(bookRepository).getById(bookId);
        Mockito.verify(bookChecker).checkValidForUpdatingStock(book, bookId, amount);
        Mockito.verify(bookRepository).update(book);
    }

    @Test
    public void testUpdateStockAmount() {
        Book book = TestEntityBuilder.createBook(1L, 1L);

        Mockito.when(bookRepository.getById(book.getId())).thenReturn(book);
        Mockito.when(bookChecker.checkValidForUpdatingStock(book, book.getId(), 0L)).thenReturn(true);

        bookService.updateStockAmount(book);

        Mockito.verify(bookRepository).getById(book.getId());
        Mockito.verify(bookChecker).checkValidForUpdatingStock(book, book.getId(), 0L);
        Mockito.verify(bookRepository).update(book);
    }

    @Test
    public void testGetBookDetail() {
        Long bookId = 1L;
        Book book = TestEntityBuilder.createBook(bookId, 1L);

        Mockito.when(bookChecker.checkExists(bookId)).thenReturn(true);
        Mockito.when(bookRepository.getById(bookId)).thenReturn(book);

        GetBookDetailDTO getBookDetailDTO = bookService.getBookDetail(bookId);

        Assertions.assertEquals(book.getBookName(), getBookDetailDTO.getName());
        Assertions.assertEquals(book.getUniqueIndex(), getBookDetailDTO.getUniqueIndex());

        Mockito.verify(bookChecker).checkExists(bookId);
        Mockito.verify(bookRepository).getById(bookId);
    }
}
