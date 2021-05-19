package ru.otus.spring.kreidun.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.kreidun.models.Genre;

public interface GenreRepository
        extends ReactiveMongoRepository<Genre, String> {
    Flux<Genre> findAll();
    Mono<Genre> findByName(String name);
    Mono<Genre> save(Mono<Genre> comment);
}
