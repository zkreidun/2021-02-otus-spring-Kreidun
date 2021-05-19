package ru.otus.spring.kreidun.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.kreidun.models.Book;


public interface BookRepository
        extends ReactiveMongoRepository<Book, String> {
    Flux<Book> findAll();
    Flux<Book> findByTitle(String title);
    Mono<Boolean> existsByAuthorId(String authorId);
    Mono<Boolean> existsByGenreId(String genreId);
    Mono<Book> save(Mono<Book> book);
}
