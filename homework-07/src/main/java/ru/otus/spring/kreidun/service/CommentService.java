package ru.otus.spring.kreidun.service;

public interface CommentService {

    boolean add(String text, long bookId);
    boolean del(long commentId);
    boolean upd(long commentId, String text, long bookId);
    void showAll();
}
