package com.getir.readingisgood.book.repository;

import com.getir.readingisgood.book.entity.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void add(Book book) {
        entityManager.persist(book);
    }

    public Book getById(Long id) {
        return entityManager.find(Book.class, id);
    }

    public void update(Book book) {
        entityManager.merge(book);
    }
}
