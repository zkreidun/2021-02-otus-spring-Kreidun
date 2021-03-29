package ru.otus.spring.kreidun.service;

public interface CommentService {

    boolean addNewComment(String text, Long bookId);
    boolean deleteComment(Long commentId);
    boolean updateComment(Long commentId, String text, Long bookId);
    void showAllComments();
}
