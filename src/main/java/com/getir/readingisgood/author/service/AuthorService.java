package com.getir.readingisgood.author.service;

import com.getir.readingisgood.author.entity.Author;
import com.getir.readingisgood.author.repository.AuthorRepository;
import com.getir.readingisgood.common.annotations.SaveEntityLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Transactional
    @SaveEntityLogger
    public void getIfExistsOrAdd(Author author) {
        Author authorDb = authorRepository.getByUniqueIndex(author.getUniqueIndex());
        if (authorDb != null) {
            author.copyFromAnotherObject(authorDb);
        } else {
            authorRepository.add(author);
        }
    }
}
