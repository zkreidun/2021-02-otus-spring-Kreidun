package ru.otus.spring.kreidun.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.kreidun.domain.Author;
import ru.otus.spring.kreidun.domain.Book;
import ru.otus.spring.kreidun.domain.Genre;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Dao с книгами должно")
@JdbcTest
@Import({BookDaoJdbc.class, GenreDaoJdbc.class,AuthorDaoJdbc.class})
class BookDaoJdbcTest {

    private static final int EXPECTED_BOOK_COUNT = 2;
    private static final int NEW_BOOK_ID = 3;
    private static final String NEW_BOOK_TITLE = "Новая книга";
    private static final int NEW_BOOK_AUTHORID = 2;
    private static final int NEW_BOOK_GENREID = 3;
    private static final String OLD_BOOK_TITLE = "Евгений Онегин";
    private static final long DEFAULT_BOOK_ID = 1;

    @Autowired
    private BookDaoJdbc bookDao;

    @Autowired
    private AuthorDaoJdbc authorDao;

    @Autowired
    private GenreDaoJdbc genreDao;

    @DisplayName("возвращать ожидаемое количество книг")
    @Test
    void shouldReturnExpectedBookCount() {
        assertThat(bookDao.count()).isEqualTo(EXPECTED_BOOK_COUNT);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("добавлять книгу")
    @Test
    void shouldInsertBook() {

        Author author = authorDao.getById(NEW_BOOK_AUTHORID);
        Genre genre = genreDao.getById(NEW_BOOK_GENREID);
        Book expectedBook = new Book(NEW_BOOK_ID, NEW_BOOK_TITLE, author, genre);
        long newId = bookDao.insert(NEW_BOOK_TITLE, NEW_BOOK_AUTHORID, NEW_BOOK_GENREID);
        Book actualBook = bookDao.getById(newId);
        assertThat(actualBook.getTitle()).isEqualTo(expectedBook.getTitle());
        assertThat(actualBook.getGenre().getName()).isEqualTo(expectedBook.getGenre().getName());
        assertThat(actualBook.getAuthor().getFirstName()).isEqualTo(expectedBook.getAuthor().getFirstName());
        assertThat(actualBook.getAuthor().getLastName()).isEqualTo(expectedBook.getAuthor().getLastName());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("изменить книгу")
    @Test
    void shouldUpdateBook() {

        Author author = authorDao.getById(NEW_BOOK_AUTHORID);
        Genre genre = genreDao.getById(NEW_BOOK_GENREID);
        Book expectedBook = new Book(NEW_BOOK_ID, NEW_BOOK_TITLE, author, genre);
        bookDao.update(DEFAULT_BOOK_ID, NEW_BOOK_TITLE, NEW_BOOK_AUTHORID, NEW_BOOK_GENREID);
        Book actualBook = bookDao.getById(DEFAULT_BOOK_ID);
        assertThat(actualBook.getTitle()).isEqualTo(expectedBook.getTitle());
        assertThat(actualBook.getGenre().getName()).isEqualTo(expectedBook.getGenre().getName());
        assertThat(actualBook.getAuthor().getFirstName()).isEqualTo(expectedBook.getAuthor().getFirstName());
        assertThat(actualBook.getAuthor().getLastName()).isEqualTo(expectedBook.getAuthor().getLastName());
    }

    @DisplayName("удалять книгу по id")
    @Test
    void shouldCorrectDeleteBookById() {
        assertThatCode(() -> bookDao.getById(DEFAULT_BOOK_ID))
                .doesNotThrowAnyException();

        bookDao.deleteById(DEFAULT_BOOK_ID);

        assertThatThrownBy(() -> bookDao.getById(DEFAULT_BOOK_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("возвращать ожидаемую книгу по id")
    @Test
    void shouldReturnExpectedBook() {
        Book book = bookDao.getById(DEFAULT_BOOK_ID);
        assertThat(book.getTitle()).isEqualTo(OLD_BOOK_TITLE);
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBooksList() {

        assertThat(bookDao.getAll()).extracting(Book::getId, Book::getTitle)
                .containsExactlyInAnyOrder(
                        tuple(1L, "Евгений Онегин"),
                        tuple(2L, "Час Быка")
                );
    }
}