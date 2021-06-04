package ru.otus.spring.kreidun.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import ru.otus.spring.kreidun.models.Book;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface BookRepository extends CrudRepository<Book, Long> {

    @PostFilter("hasPermission(filterObject, 'READ')")
    @EntityGraph(value = "allJoin", type = EntityGraph.EntityGraphType.FETCH)
    List<Book> findAll();

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    @EntityGraph(value = "allJoin", type = EntityGraph.EntityGraphType.FETCH)
    List<Book> findAllByAuthorId(Long id);

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    @EntityGraph(value = "allJoin", type = EntityGraph.EntityGraphType.FETCH)
    List<Book> findAllByAuthorLastName(String lastName);

    @PostAuthorize("hasPermission(returnObject, 'READ')")
    @EntityGraph(value = "allJoin", type = EntityGraph.EntityGraphType.FETCH)
    List<Book> findAllByGenreName(String genreName);

    boolean existsByAuthorId(long authorId);

    boolean existsByGenreId(long genreId);

    Book save(Book book);

    @PostFilter("hasPermission(filterObject, 'READ')")
    long count();

}
