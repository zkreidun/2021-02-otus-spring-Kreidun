package ru.otus.spring.kreidun.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.kreidun.controllers.dto.GenreDto;

import ru.otus.spring.kreidun.repositories.BookRepository;
import ru.otus.spring.kreidun.repositories.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GenreApiController {

    private final GenreRepository repository;
    private final BookRepository bookRepository;

    @Autowired
    public GenreApiController(GenreRepository genreRepository, BookRepository bookRepository) {

        this.repository = genreRepository;
        this.bookRepository = bookRepository;
    }


    @GetMapping("/api/genres")
    public List<GenreDto> getListGenresApi() {

        return repository.findAll().stream().map(GenreDto::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/api/genres/{id}")
    public void  deleteGenreApi(@PathVariable("id") long id) throws Exception {

        if (bookRepository.existsByGenreId(id)) {
            throw new Exception("У данного жанра есть книги, удаление невозможно!");
        }
        repository.deleteById(id);
    }
}
