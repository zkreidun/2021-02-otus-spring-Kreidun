package ru.otus.spring.kreidun.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.kreidun.models.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {}
