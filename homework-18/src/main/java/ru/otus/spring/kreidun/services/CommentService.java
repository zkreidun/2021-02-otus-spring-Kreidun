package ru.otus.spring.kreidun.services;

import ru.otus.spring.kreidun.models.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findCommentByBookId(Long id);
    List<Comment> getAll();
    void deleteAllCommentsByBookId(long bookId);
}
