package ru.otus.spring.kreidun.service;

public interface BookService {

    boolean add(String bookTitle, String authorId, String genreId);
    boolean del(String id);
    boolean upd(String id, String bookTitle, String authorId, String genreId);
    void showAll();
    void showByAuthorId(String author_id);
}
