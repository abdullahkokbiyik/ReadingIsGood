package com.getir.readingisgood.integration.book.repository;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.repository.BookRepository;
import com.getir.readingisgood.common.AbstractIntegrationTest;
import com.getir.readingisgood.common.TestEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestBookRepository extends AbstractIntegrationTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testGetById() {
        Book book = bookRepository.getById(10000L);

        Assertions.assertNotNull(book);
        Assertions.assertEquals(book.getUniqueIndex(), "testuidbook");
    }

    @Test
    public void testAdd() {
        Book book = TestEntityBuilder.createBook(null, 10000L);

        bookRepository.add(book);

        Book bookDB = bookRepository.getByUniqueIndex(book.getUniqueIndex());

        Assertions.assertNotNull(bookDB);
    }

    @Test
    public void testUpdate() {
        Book book = TestEntityBuilder.createBook(10000L, 10000L);

        bookRepository.update(book);

        Book bookDB = bookRepository.getByUniqueIndex(book.getUniqueIndex());

        Assertions.assertNotNull(bookDB);
        Assertions.assertEquals(book.getUniqueIndex(), bookDB.getUniqueIndex());
        Assertions.assertEquals(book.getBookName(), bookDB.getBookName());
    }

    @Test
    public void testGetByUniqueIndex() {
        String uniqueIndex = "testuidbook";

        Book book = bookRepository.getByUniqueIndex(uniqueIndex);

        Assertions.assertNotNull(book);
        Assertions.assertEquals(book.getId(), Long.valueOf(10000));
    }
}
