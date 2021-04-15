package ru.otus.spring.kreidun.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.kreidun.models.Book;
import ru.otus.spring.kreidun.models.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Repository с комментами должен")
@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.spring.kreidun.config", "ru.otus.spring.kreidun.repositories"})
class CommentRepositoryTest {

    @Autowired
    private  BookRepository bookRepository;

    @Autowired
    private  CommentRepository commentRepository;

    @DisplayName("удалять комментарий по Id книги")
    @Test
    void shouldDeleteCommentByBookId() {

        Book book = bookRepository.findAll().get(0);
        commentRepository.deleteByBookId(book.getId());
        List<Comment> comments = commentRepository.findAll();
        comments.forEach(a ->assertThat(a.getBook().getId()).isNotEqualTo(book.getId()));

    }
}