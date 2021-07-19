package ru.otus.spring.kreidun.services;

import ru.otus.spring.kreidun.models.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAll();
    Genre findGenreByName(String name);
}
