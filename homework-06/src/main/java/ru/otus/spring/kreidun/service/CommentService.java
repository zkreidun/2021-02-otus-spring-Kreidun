package ru.otus.spring.kreidun.service;

public interface CommentService {

    boolean add(String text, Long bookId);
    boolean del(Long commentId);
    boolean upd(Long commentId, String text, Long bookId);
    void showAll();
}
