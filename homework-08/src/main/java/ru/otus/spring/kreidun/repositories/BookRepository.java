package ru.otus.spring.kreidun.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.kreidun.models.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findAllByAuthorId(String authorId);
}
