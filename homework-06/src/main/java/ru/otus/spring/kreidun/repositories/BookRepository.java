package ru.otus.spring.kreidun.repositories;

import ru.otus.spring.kreidun.models.Book;

import java.util.List;

public interface BookRepository {

    int count();
    Book save(Book book);
    void deleteById (long id);
    Book getById (long id);
    List<Book> getAll();
}
