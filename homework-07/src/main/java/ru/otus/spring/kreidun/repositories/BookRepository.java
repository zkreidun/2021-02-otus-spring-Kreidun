package ru.otus.spring.kreidun.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.kreidun.models.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findById(long id);

    @EntityGraph(value = "allJoin", type = EntityGraph.EntityGraphType.FETCH)
    List<Book> findAll();

    @EntityGraph(value = "allJoin", type = EntityGraph.EntityGraphType.FETCH)
    List<Book> findAllByAuthorLastName(String lastName);

    @EntityGraph(value = "allJoin", type = EntityGraph.EntityGraphType.FETCH)
    List<Book> findAllByGenreName(String genreName);
}
