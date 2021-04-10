package ru.otus.spring.kreidun.repositories;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository с комментариями должен")
@DataJpaTest
class CommentRepositoryTest {

    private static final int EXPECTED_NUMBER_OF_COMMENTS = 3;
    private static final long FIRST_BOOK_ID = 1L;

    @Autowired
    private CommentRepository repositoryComment;

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

        val comments = repositoryComment.findByBookId(FIRST_BOOK_ID);
        comments.forEach(a ->assertThat(a.getBook().getId()).isEqualTo(FIRST_BOOK_ID));
    }

    @DisplayName("загружать список всех комментариев")
    @Test
    void shouldReturnCorrectCommentList() {

        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        val comments = repositoryComment.findAll();
        assertThat(comments).isNotNull().hasSize(EXPECTED_NUMBER_OF_COMMENTS).allMatch(s -> !s.getText().equals(""));
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
    }

    @DisplayName("удалять комментарии для книги по id")
    @Test
    void shouldDeleteCommentByBookId() {

        repositoryComment.deleteByBookId(FIRST_BOOK_ID);
        val comments = repositoryComment.findByBookId(FIRST_BOOK_ID);
        assertThat(comments).isEmpty();
    }
}