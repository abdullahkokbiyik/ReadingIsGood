package com.getir.readingisgood.integration.book.repository;

import com.getir.readingisgood.book.entity.Book;
import com.getir.readingisgood.book.repository.BookRepository;
import com.getir.readingisgood.common.AbstractIntegrationTest;
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
    }
}
