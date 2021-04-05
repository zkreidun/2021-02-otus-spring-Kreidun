package ru.otus.spring.kreidun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.kreidun.models.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>
{
    List<Comment> findByBookId(long id);
    List<Comment> findAll();
    void deleteByBookId(long bookId);
}