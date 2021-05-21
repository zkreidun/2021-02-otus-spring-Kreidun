package ru.otus.spring.kreidun.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.kreidun.models.Author;

public interface AuthorRepository
        extends ReactiveMongoRepository<Author, String> {
    Flux<Author> findByLastName(String lastName);
    Mono<Author> save(Mono<Author> author);
}
