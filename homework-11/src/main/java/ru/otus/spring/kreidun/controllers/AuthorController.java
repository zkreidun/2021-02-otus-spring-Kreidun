package ru.otus.spring.kreidun.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.kreidun.models.Author;
import ru.otus.spring.kreidun.repositories.AuthorRepository;
import ru.otus.spring.kreidun.repositories.BookRepository;


@RestController
public class AuthorController {

    private final AuthorRepository repository;
    private final BookRepository bookRepository;

    public AuthorController(AuthorRepository repository, BookRepository bookRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/api/authors")
    public Flux<Author> getViewAuthors() {
        return repository.findAll();
    }

    @GetMapping("/api/authors/{id}")
    public Mono<Author> getAuthorById(@PathVariable("id") String id) {
        return repository.findById(id);
    }

    @PostMapping("/api/authors")
    public Mono<Author> saveAuthor(@RequestBody Mono<Author> monoAuthor) {
        return repository.save(monoAuthor);
    }

    @GetMapping("/api/authors/find/{lastname}")
    public Flux<Author> getAuthorByName(@PathVariable("lastname") String lastname) {
        return repository.findByLastName(lastname);
    }

    @DeleteMapping("/api/authors/{id}")
    public Mono<ResponseEntity<Void>> deleteAuthorById(@PathVariable("id") String id) {
        return repository.existsById(id)

                .flatMap(
                        existingAuthor ->
                                bookRepository.existsByAuthorId(id)
                ).filter(isExist -> !isExist)
                .flatMap(isExist ->
                        repository.deleteById(id)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

}
