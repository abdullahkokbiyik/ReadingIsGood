package com.getir.readingisgood.book.repository;

import com.getir.readingisgood.book.entity.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

    public Book getByUniqueIndex(String uniqueIndex) {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b where b.uniqueIndex = :uniqueIndex ", Book.class);
        query.setParameter("uniqueIndex", uniqueIndex);
        return query.getResultList().stream().findFirst().orElse(null);
    }
}
