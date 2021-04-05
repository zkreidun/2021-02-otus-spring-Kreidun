package ru.otus.spring.kreidun.service;

public interface BookService {

    boolean add(String bookTitle, long authorId, long genreId);
    boolean del(long id);
    boolean upd(long id, String bookTitle, long authorId, long genreId);
    void showAll();
    void showByAuthorId(long author_id);
}
