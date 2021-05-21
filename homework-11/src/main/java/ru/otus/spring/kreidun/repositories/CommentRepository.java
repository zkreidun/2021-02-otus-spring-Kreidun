package ru.otus.spring.kreidun.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.kreidun.models.Comment;

public interface CommentRepository
        extends ReactiveMongoRepository<Comment, String> {
    Flux<Comment> findAll();
    Mono<Comment> save(Mono<Comment> comment);
    Mono<Boolean> existsByBookId(String bookId);
}