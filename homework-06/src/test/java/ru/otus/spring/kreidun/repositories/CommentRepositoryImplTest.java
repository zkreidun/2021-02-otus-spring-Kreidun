package ru.otus.spring.kreidun.repositories;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.kreidun.models.Book;
import ru.otus.spring.kreidun.models.Comment;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository с комментариями должен")
@DataJpaTest
@Import(CommentRepositoryImpl.class)
class CommentRepositoryImplTest {

    private static final int EXPECTED_NUMBER_OF_COMMENTS = 3;
    private static final long FIRST_COMMENT_ID = 1L;
    private static final long FIRST_BOOK_ID = 1L;
    private static final long SECOND_BOOK_ID = 2L;
    private static final String FIRST_COMMENT_TEXT = "Превосходно";
    private static final String NEW_COMMENT_TEXT = "New Comment";
    private static final int EXPECTED_QUERIES_COUNT = 1;

    @Autowired
    private CommentRepositoryImpl repositoryComment;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемое количество комментариев")
    @Test
    void shouldReturnExpectedCommentCount() {
        assertThat(repositoryComment.count()).isEqualTo(EXPECTED_NUMBER_OF_COMMENTS);
    }

    @DisplayName("возвращать комментарий по его id")
    @Test
    void shouldFindCommentById() {

        Optional<Comment> comment = Optional.ofNullable(repositoryComment.findById(FIRST_COMMENT_ID));
        assertThat(comment).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("text", FIRST_COMMENT_TEXT);
    }

    @DisplayName("загружать список всех комментариев")
    @Test
    void shouldReturnCorrectCommentList() {

        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        val comments = repositoryComment.getAll();
        assertThat(comments).isNotNull().hasSize(EXPECTED_NUMBER_OF_COMMENTS).allMatch(s -> !s.getText().equals(""));
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
    }

    @DisplayName("корректно сохранять комментарий")
    @Test
    void shouldSaveAllAuthorInfo()  {

        Book book =  em.find(Book.class, FIRST_BOOK_ID);
        Comment comment = new Comment(0, NEW_COMMENT_TEXT, book);
        repositoryComment.save(comment);
        assertThat(comment.getId()).isGreaterThan(0);

        Comment actualComment = em.find(Comment.class, comment.getId());
        assertThat(actualComment).isNotNull().matches(s -> s.getText().equals(NEW_COMMENT_TEXT));
    }

    @DisplayName("удалять комментарий по его id")
    @Test
    void shouldDeleteCommentById() {

        val firstComment = em.find(Comment.class, FIRST_COMMENT_ID);
        assertThat(firstComment).isNotNull();
        em.detach(firstComment);

        repositoryComment.deleteById(FIRST_COMMENT_ID);
        val deletedComment = em.find(Comment.class, FIRST_COMMENT_ID);

        assertThat(deletedComment).isNull();
    }

    @DisplayName("изменять комментарий по его id")
    @Test
    void shouldUpdateCommentById() {

        Comment firstComment = em.find(Comment.class, FIRST_COMMENT_ID);
        assertThat(firstComment).isNotNull();
        em.detach(firstComment);

        repositoryComment.update(FIRST_COMMENT_ID, NEW_COMMENT_TEXT, SECOND_BOOK_ID);
        firstComment = em.find(Comment.class, FIRST_COMMENT_ID);
        assertThat(firstComment.getText()).isEqualTo(NEW_COMMENT_TEXT);
        assertThat(firstComment.getBook().getId()).isEqualTo(SECOND_BOOK_ID);
    }
}