package ru.otus.spring.kreidun.dao;

import ru.otus.spring.kreidun.domain.Author;

import java.util.List;

public interface AuthorDao {

    int count();
    Long insert (String firstName, String lastName);
    Author getById(long id);
    List<Author> getAll();
}
