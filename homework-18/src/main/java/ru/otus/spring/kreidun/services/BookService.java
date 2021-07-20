package ru.otus.spring.kreidun.services;

import ru.otus.spring.kreidun.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAll();
    List<Book> findAllBooksByAuthorId(Long id);
    List<Book> findAllBooksByAuthorLastName(String lastName);
    List<Book> findAllBooksByGenreName(String genreName);
    Optional<Book> findBookById(Long id);
    Book saveBook(Book book);
    void deleteBookById(Long id);
}
