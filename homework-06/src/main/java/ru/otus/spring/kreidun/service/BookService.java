package ru.otus.spring.kreidun.service;

public interface BookService {

    boolean addNewBook(String bookTitle, Long authorId, Long genreId);
    boolean delBook(long id);
    boolean updBook(long id, String bookTitle, long authorId, long genreId);
    void showAllBooks();
}
