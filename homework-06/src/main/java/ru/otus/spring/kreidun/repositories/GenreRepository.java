package ru.otus.spring.kreidun.repositories;

import ru.otus.spring.kreidun.models.Genre;

import java.util.List;

public interface GenreRepository {
    int count();
    Genre save (Genre genre);
    Genre findById(long id);
    List<Genre> getAll();
}
