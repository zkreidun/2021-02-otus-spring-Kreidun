package ru.otus.spring.kreidun.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.test.context.support.WithMockUser;
import javax.sql.DataSource;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с книгами должно")
@DataJpaTest
@ComponentScan({"ru.otus.spring.kreidun.services","ru.otus.spring.kreidun.security"})
class BookRepositoryTest {

    private static final long EXPECTED_BOOKS_COUNT_FOR_ADMIN = 2;
    private static final long EXPECTED_BOOKS_COUNT_FOR_USER = 1;
    private static final long EXPECTED_BOOKS_COUNT_FOR_TEST = 0;

    @Autowired
    private DataSource dataSource;
    @Autowired private BookRepository bookRepository;
    @Autowired private GenreRepository genreRepository;
    @Autowired private AuthorRepository authorRepository;
    @Autowired private CommentRepository commentRepository;
    @Autowired private UserRepository userRepository;


    @DisplayName("возвращать ожидаемое количество книг для пользователя ADMIN")
    @Test
    @WithMockUser(username = "admin" , password = "1")
    void shouldReturnExpectedBooksForAdmin() {
        assertThat(bookRepository.findAll().size()).isEqualTo(EXPECTED_BOOKS_COUNT_FOR_ADMIN);
    }
    @DisplayName("возвращать ожидаемое количество книг для пользователя USER")
    @Test
    @WithMockUser(username = "user" , password = "1")
    void shouldReturnExpectedBooksForUser() {
        assertThat(bookRepository.findAll().size()).isEqualTo(EXPECTED_BOOKS_COUNT_FOR_USER);
    }
    @DisplayName("возвращать ожидаемое количество книг для пользователя TEST")
    @Test
    @WithMockUser(username = "test" , password = "1")
    void shouldReturnExpectedBooksForTest() {
        assertThat(bookRepository.findAll().size()).isEqualTo(EXPECTED_BOOKS_COUNT_FOR_TEST);
    }

}