package com.getir.readingisgood.unit.book.checker;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.repository.BookRepository;
import com.getir.readingisgood.book.service.checker.BookChecker;
import com.getir.readingisgood.book.service.message.BookMessages;
import com.getir.readingisgood.common.AbstractUnitTest;
import com.getir.readingisgood.common.TestEntityBuilder;
import com.getir.readingisgood.common.context.MessageContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TestBookChecker extends AbstractUnitTest {

    @InjectMocks
    private BookChecker bookChecker;

    @Mock
    private MessageContext messageContext;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testCheckValidForUpdatingStock() {
        Long bookId = 1L;
        Book book = TestEntityBuilder.createBook(bookId, 1L);

        Mockito.when(bookRepository.getById(bookId)).thenReturn(book);

        Assertions.assertTrue(bookChecker.checkValidForUpdatingStock(book, bookId, 5L));

        Mockito.verify(bookRepository).getById(bookId);
    }

    @Test
    public void testCheckNotValidForUpdatingStockBookNotExists() {
        Long bookId = 1L;

        Mockito.when(bookRepository.getById(bookId)).thenReturn(null);

        Assertions.assertFalse(bookChecker.checkValidForUpdatingStock(null, bookId, 5L));

        Mockito.verify(bookRepository).getById(bookId);
        Mockito.verify(messageContext).addErrorMessage(BookMessages.ERROR_BOOK_DOES_NOT_EXIST, "id", bookId);
    }

    @Test
    public void testCheckNotValidForUpdatingStockOutOfStock() {
        Long bookId = 1L;
        Book book = TestEntityBuilder.createBook(bookId, 1L);

        Mockito.when(bookRepository.getById(bookId)).thenReturn(book);

        Assertions.assertFalse(bookChecker.checkValidForUpdatingStock(book, bookId, 35L));

        Mockito.verify(bookRepository).getById(bookId);
        Mockito.verify(messageContext).addErrorMessage(BookMessages.ERROR_BOOK_IS_OUT_OF_STOCK);
    }

    @Test
    public void testCheckExists() {
        Long bookId = 1L;
        Book book = TestEntityBuilder.createBook(bookId, 1L);

        Mockito.when(bookRepository.getById(bookId)).thenReturn(book);

        Assertions.assertTrue(bookChecker.checkExists(bookId));

        Mockito.verify(bookRepository).getById(bookId);
    }

    @Test
    public void testCheckNotExists() {
        Long bookId = 1L;

        Mockito.when(bookRepository.getById(bookId)).thenReturn(null);

        Assertions.assertFalse(bookChecker.checkExists(bookId));

        Mockito.verify(bookRepository).getById(bookId);
        Mockito.verify(messageContext).addErrorMessage(BookMessages.ERROR_BOOK_DOES_NOT_EXIST, "id", bookId);
    }

    @Test
    public void testCheckExistsUniqueIndex() {
        Book book = TestEntityBuilder.createBook(1L, 1L);

        Assertions.assertTrue(bookChecker.checkExists(book, book.getUniqueIndex()));
    }

    @Test
    public void testCheckNotExistsUniqueIndex() {
        String uniqueIndex = "test";

        Assertions.assertFalse(bookChecker.checkExists(null, uniqueIndex));

        Mockito.verify(messageContext).addErrorMessage(BookMessages.ERROR_BOOK_DOES_NOT_EXIST, "unique index", uniqueIndex);
    }
}
