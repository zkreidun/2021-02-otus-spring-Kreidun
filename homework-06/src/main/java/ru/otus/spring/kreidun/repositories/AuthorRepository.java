package ru.otus.spring.kreidun.repositories;

import ru.otus.spring.kreidun.models.Author;

import java.util.List;

public interface AuthorRepository {
    int count();
    Author save(Author author);
    Author findById(long id);
    List<Author> getAll();
}
