package ru.otus.spring.kreidun.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.spring.kreidun.models.Genre;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@DisplayName("GenreRepository должен")
public class GenreRepositoryTest {

    @Autowired
    private  GenreRepository repository;

    @DisplayName("устанавливать ИД при сохранении")
    @Test
    public void shouldSetIdOnSave() {
        Mono<Genre> genreMono = repository.save(new Genre("test"));

        StepVerifier
                .create(genreMono)
                .assertNext(genre -> assertNotNull(genre.getId()))
                .expectComplete()
                .verify();
    }

    @DisplayName("находить жанры по наименованию")
    @Test
    public void shouldFindByName() {
        repository.save(new Genre("test")).block();

        StepVerifier.create(
                repository.findByName("test")
        )
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }
}