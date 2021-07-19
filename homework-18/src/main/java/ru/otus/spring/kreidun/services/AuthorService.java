package ru.otus.spring.kreidun.services;

import ru.otus.spring.kreidun.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> getAll();
    Optional<Author> findAuthorById(Long id);
    List<Author> findAuthorByLastName(String lastName);
}
