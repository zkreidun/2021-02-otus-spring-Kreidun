package ru.otus.spring.kreidun.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.kreidun.models.Comment;

public interface CommentRepository extends MongoRepository<Comment, String>
{

    void deleteByBookId(String book_id);
}