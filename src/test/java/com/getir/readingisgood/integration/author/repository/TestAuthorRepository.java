package com.getir.readingisgood.integration.author.repository;

import com.getir.readingisgood.author.entity.Author;
import com.getir.readingisgood.author.repository.AuthorRepository;
import com.getir.readingisgood.common.AbstractIntegrationTest;
import com.getir.readingisgood.common.TestEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestAuthorRepository extends AbstractIntegrationTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testAdd() {
        Author author = TestEntityBuilder.createAuthor(null);

        authorRepository.add(author);

        Author authorDB = authorRepository.getByUniqueIndex(author.getUniqueIndex());
        Assertions.assertNotNull(authorDB);
    }

    @Test
    public void testGetByUniqueIndex() {
        String uniqueIndex = "testuidauthor";

        Author author = authorRepository.getByUniqueIndex(uniqueIndex);

        Assertions.assertNotNull(author);
        Assertions.assertEquals(author.getId(), Long.valueOf(10000));
    }
}
