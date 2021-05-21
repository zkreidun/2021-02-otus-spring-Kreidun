package ru.otus.spring.kreidun.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.kreidun.models.Book;
import ru.otus.spring.kreidun.repositories.BookRepository;
import ru.otus.spring.kreidun.repositories.CommentRepository;


@RestController
public class BookController {

    private final BookRepository repository;
    private final CommentRepository commentRepository;

    public BookController(BookRepository repository, CommentRepository commentRepository) {
        this.repository = repository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/api/books")
    public Flux<Book> getViewBooks() {
        return repository.findAll();
    }

    @GetMapping("/api/books/{id}")
    public Mono<Book> byId(@PathVariable("id") String id) {
        return repository.findById(id);
    }

    @PostMapping("/api/books")
    public Mono<Book> saveBook(@RequestBody Mono<Book> monoBook) {
        return repository.save(monoBook);
    }

    @GetMapping("/api/books/find/{title}")
    public Flux<Book> byName(@PathVariable("title") String title) {
        return repository.findByTitle(title);
    }

    @DeleteMapping("/api/books/{id}")
    public Mono<ResponseEntity<Void>> deleteBookById(@PathVariable("id") String id) {

        return repository.existsById(id)

                .flatMap(
                        existingBook ->
                                commentRepository.existsByBookId(id)
                ).filter(isExist -> !isExist)
                .flatMap(isExist ->
                        repository.deleteById(id)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
