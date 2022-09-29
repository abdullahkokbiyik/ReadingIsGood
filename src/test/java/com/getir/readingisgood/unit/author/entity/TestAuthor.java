package com.getir.readingisgood.unit.author.entity;

import com.getir.readingisgood.author.entity.Author;
import com.getir.readingisgood.common.AbstractUnitTest;
import com.getir.readingisgood.common.TestEntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestAuthor extends AbstractUnitTest {

    @Test
    public void testCopyFromAnotherObject() {
        Author firstAuthor = new Author();
        Author secondAuthor = TestEntityBuilder.createAuthor(10L);

        firstAuthor.copyFromAnotherObject(secondAuthor);

        Assertions.assertEquals(firstAuthor.getId(), secondAuthor.getId());
        Assertions.assertEquals(firstAuthor.getUniqueIndex(), secondAuthor.getUniqueIndex());
        Assertions.assertEquals(firstAuthor.getAuthorName(), secondAuthor.getAuthorName());
    }
}
