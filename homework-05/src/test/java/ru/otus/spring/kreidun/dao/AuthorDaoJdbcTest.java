package ru.otus.spring.kreidun.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.kreidun.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@DisplayName("Dao с авторами должно")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    private static final int EXPECTED_AUTHORS_COUNT = 2;
    private static final int NEW_AUTHOR_ID = 3;
    private static final String NEW_AUTHOR_FIRSTNAME = "ИМЯ";
    private static final String NEW_AUTHOR_LASTNAME = "ФАМИЛИЯ";
    private static final String OLD_AUTHOR_FIRSTNAME = "Александр";
    private static final String OLD_AUTHOR_LASTNAME = "Пушкин";
    private static final long DEFAULT_AUTHOR_ID = 1;

    @Autowired
    private AuthorDaoJdbc authorDao;

    @DisplayName("возвращать ожидаемое количество авторов")
    @Test
    void shouldReturnExpectedAuthorCount() {
        assertThat(authorDao.count()).isEqualTo(EXPECTED_AUTHORS_COUNT);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("добавлять автора")
    @Test
    void shouldInsertAuthor() {

        Author expectedAuthor = new Author(NEW_AUTHOR_ID, NEW_AUTHOR_FIRSTNAME, NEW_AUTHOR_LASTNAME);
        long newId = authorDao.insert(NEW_AUTHOR_FIRSTNAME, NEW_AUTHOR_LASTNAME);
        Author actualAuthor = authorDao.getById(newId);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("возвращать ожидаемого автора по id")
    @Test
    void shouldReturnExpectedAuthor() {

        Author expectedAuthor = new Author(DEFAULT_AUTHOR_ID, OLD_AUTHOR_FIRSTNAME, OLD_AUTHOR_LASTNAME);
        Author actualAuthor = authorDao.getById(DEFAULT_AUTHOR_ID);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("возвращать ожидаемый список авторов")
    @Test
    void shouldReturnExpectedAuthorsList() {

        assertThat(authorDao.getAll()).extracting(Author::getId, Author::getFirstName, Author::getLastName)
                .containsExactlyInAnyOrder(
                        tuple(1L, "Александр", "Пушкин"),
                        tuple(2L, "Иван", "Ефремов")
                );

/*
        Author expectedAuthor = new Author(DEFAULT_AUTHOR_ID, OLD_AUTHOR_FIRSTNAME, OLD_AUTHOR_LASTNAME);
        List<Author> actualAuthorList = authorDao.getAll();
        assertThat(actualAuthorList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedAuthor);
*/
    }

}