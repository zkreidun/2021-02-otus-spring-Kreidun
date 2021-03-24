package ru.otus.spring.kreidun.dao;

import ru.otus.spring.kreidun.domain.Book;

import java.util.List;

public interface BookDao {

    int count();
    long insert (String bookTitle, long authorId, long genreId);
    void update (long id, String bookTitle, long authorId, long genreId);
    void deleteById (long id);
    Book getById (long id);
    List<Book> getAll();
}
