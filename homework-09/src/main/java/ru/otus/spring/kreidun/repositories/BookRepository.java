package ru.otus.spring.kreidun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.kreidun.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findById(long id);
}
