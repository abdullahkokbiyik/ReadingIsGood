package com.getir.readingisgood.author.repository;

import com.getir.readingisgood.author.entity.Author;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class AuthorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void add(Author author) {
        entityManager.persist(author);
    }

    public Author getByUniqueIndex(String uniqueIndex) {
        TypedQuery<Author> query = entityManager.createQuery("select a from Author a where a.uniqueIndex = :uniqueIndex ", Author.class);
        query.setParameter("uniqueIndex", uniqueIndex);
        return query.getSingleResult();
    }
}
