package ru.otus.spring.kreidun.dao;

import ru.otus.spring.kreidun.domain.Genre;

import java.util.List;

public interface GenreDao {
    int count();
    long insert (String genreName);
    Genre getById(long id);
    List<Genre> getAll();
}
