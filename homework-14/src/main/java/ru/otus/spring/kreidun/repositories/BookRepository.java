package ru.otus.spring.kreidun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.kreidun.models.BookForWrite;

public interface BookRepository extends JpaRepository<BookForWrite, Long> {

    BookForWrite findByTitle(String title);
}
