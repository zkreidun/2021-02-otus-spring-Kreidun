package ru.otus.spring.kreidun.service;

public interface CommentService {

    boolean add(String text, String bookId);
    boolean del(String commentId);
    boolean upd(String commentId, String text, String bookId);
    void showAll();
}
