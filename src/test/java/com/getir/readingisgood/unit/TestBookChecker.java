package com.getir.readingisgood.unit;

import com.getir.readingisgood.book.repository.BookRepository;
import com.getir.readingisgood.book.service.checker.BookChecker;
import com.getir.readingisgood.book.service.message.BookMessages;
import com.getir.readingisgood.common.AbstractUnitTest;
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
    public void testCheckExists() {
        Long bookId = 1L;

        Mockito.when(bookRepository.getById(bookId)).thenReturn(null);

        Assertions.assertFalse(bookChecker.checkExists(bookId));

        Mockito.verify(bookRepository).getById(bookId);
        Mockito.verify(messageContext).addErrorMessage(BookMessages.ERROR_BOOK_DOES_NOT_EXIST, bookId);
    }
}
