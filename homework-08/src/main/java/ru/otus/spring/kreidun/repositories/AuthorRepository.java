package ru.otus.spring.kreidun.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.kreidun.models.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String>{
    List<Author> findByLastName(String lastname);
}
