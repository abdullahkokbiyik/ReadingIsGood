package com.getir.readingisgood.unit.author.service;

import com.getir.readingisgood.author.entity.Author;
import com.getir.readingisgood.author.repository.AuthorRepository;
import com.getir.readingisgood.author.service.AuthorService;
import com.getir.readingisgood.common.AbstractUnitTest;
import com.getir.readingisgood.common.TestEntityBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TestAuthorService extends AbstractUnitTest {

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepository authorRepository;

    @Test
    public void testIfExistsOrAddNotExists() {
        Author author = TestEntityBuilder.createAuthor(null);

        Mockito.when(authorRepository.getByUniqueIndex(author.getUniqueIndex())).thenReturn(null);

        authorService.getIfExistsOrAdd(author);

        Mockito.verify(authorRepository).getByUniqueIndex(author.getUniqueIndex());
        Mockito.verify(authorRepository).add(author);
    }

    @Test
    public void testIfExistsOrAddExists() {
        Author author = TestEntityBuilder.createAuthor(1L);

        Mockito.when(authorRepository.getByUniqueIndex(author.getUniqueIndex())).thenReturn(author);

        authorService.getIfExistsOrAdd(author);

        Mockito.verify(authorRepository).getByUniqueIndex(author.getUniqueIndex());
    }
}
