package ru.otus.spring.kreidun.controllers.dto;

import ru.otus.spring.kreidun.models.Genre;

@SuppressWarnings("all")
public class GenreDto {

    private long id = -1;
    private String name;

    public GenreDto() {
    }

    public GenreDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static GenreDto toDto(Genre genre) {

        return new GenreDto(
                genre.getId(),
                genre.getName()
        );
    }
}