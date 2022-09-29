package com.getir.readingisgood.book.service.checker;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.repository.BookRepository;
import com.getir.readingisgood.book.service.message.BookMessages;
import com.getir.readingisgood.common.context.MessageContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookChecker {

    private final MessageContext messageContext;
    private final BookRepository bookRepository;

    public boolean checkValidForUpdatingStock(Book book, Long bookId, Long amount) {
        return checkExists(bookId)
                && checkStockAmountValid(book.getStockAmount(), amount);
    }

    public boolean checkExists(Long bookId) {
        Book book = bookRepository.getById(bookId);
        if (book == null) {
            messageContext.addErrorMessage(BookMessages.ERROR_BOOK_DOES_NOT_EXIST, "id", bookId);
            return false;
        }
        return true;
    }

    public boolean checkExists(Book book, String uniqueIndex) {
        if (book == null) {
            messageContext.addErrorMessage(BookMessages.ERROR_BOOK_DOES_NOT_EXIST, "unique index", uniqueIndex);
            return false;
        }
        return true;
    }

    private boolean checkStockAmountValid(Long stockAmount, Long amount) {
        if (stockAmount - amount < 0) {
            messageContext.addErrorMessage(BookMessages.ERROR_BOOK_IS_OUT_OF_STOCK);
            return false;
        }
        return true;
    }
}
