package ru.otus.spring.kreidun.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.kreidun.models.Comment;
import ru.otus.spring.kreidun.repositories.CommentRepository;


@RestController
public class CommentController {

    private final CommentRepository repository;

    public CommentController(CommentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/comments")
    public Flux<Comment> getViewComments() {
        return repository.findAll();
    }

    @GetMapping("/api/comments/{id}")
    public Mono<Comment> byId(@PathVariable("id") String id) {
        return repository.findById(id);
    }

    @PostMapping("/api/comments")
    public Mono<Comment> saveComment(@RequestBody Mono<Comment> monoComment) {
        return repository.save(monoComment);
    }

    @DeleteMapping("/api/comments/{id}")
    public Mono<ResponseEntity<Void>> deleteCommentById(@PathVariable("id") String id) {

        return repository.existsById(id)
                .flatMap(existingComment ->
                        repository.deleteById(id)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
