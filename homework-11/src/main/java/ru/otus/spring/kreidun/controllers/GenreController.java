package ru.otus.spring.kreidun.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.kreidun.models.Genre;
import ru.otus.spring.kreidun.repositories.BookRepository;
import ru.otus.spring.kreidun.repositories.GenreRepository;


@RestController
public class GenreController {

    private final GenreRepository repository;
    private final BookRepository bookRepository;

    public GenreController(GenreRepository repository, BookRepository bookRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/api/genres")
    public Flux<Genre> getViewGenres() {
        return repository.findAll();
    }

    @GetMapping("/api/genres/{id}")
    public Mono<Genre> findCommentById(@PathVariable("id") String id) {
        return repository.findById(id);
    }

    @PostMapping(value = "/api/genres", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Genre> createGenre(@RequestBody  Mono<Genre> monoGenre) {
        return repository.save(monoGenre);
    }

    @GetMapping("/api/genres/find/{name}")
    public Mono<Genre> findGenreByName(@PathVariable("name") String name) {
        return repository.findByName(name);
    }

    @DeleteMapping("/api/genres/{id}")
    public Mono<ResponseEntity<Void>> deleteGenreById(@PathVariable("id") String id) {
        return repository.findById(id)

                .flatMap(
                        existingGenre ->
                                bookRepository.existsByGenreId(existingGenre.getId())
                ).filter(isExist -> !isExist)
                .flatMap(isExist ->
                        repository.deleteById(id)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
