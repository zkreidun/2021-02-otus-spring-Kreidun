package ru.otus.spring.kreidun.repositories;

import ru.otus.spring.kreidun.models.Comment;

import java.util.List;

public interface CommentRepository {
    int count();
    Comment save(Comment comment);
    Comment findById(long id);
    List<Comment> getAll();
    void deleteById(long id);
    void update(long id, String text, long bookId);
}
