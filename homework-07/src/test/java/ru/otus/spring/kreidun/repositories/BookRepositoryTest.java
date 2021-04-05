package ru.otus.spring.kreidun.repositories;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.kreidun.models.Author;
import ru.otus.spring.kreidun.models.Book;
import ru.otus.spring.kreidun.models.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@DisplayName("Repository с книгами должен")
@DataJpaTest
class BookRepositoryTest {

    private static final int EXPECTED_BOOK_COUNT = 2;
    private static final String NEW_BOOK_TITLE = "Новая книга";
    private static final long NEW_BOOK_AUTHORID = 2L;
    private static final long NEW_BOOK_GENREID = 3L;
    private static final String OLD_BOOK_TITLE = "Евгений Онегин";
    private static final long DEFAULT_BOOK_ID = 1;
    private static final int EXPECTED_QUERIES_COUNT = 1;
    private static final String NEW_AUTHOR_FIRSTNAME = "ИМЯ";
    private static final String NEW_AUTHOR_LASTNAME = "ФАМИЛИЯ";
    private static final String NEW_GENRE_NAME = "New genre";

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемое количество книг")
    @Test
    void shouldReturnExpectedBookCount() {
        assertThat(bookRepository.count()).isEqualTo(EXPECTED_BOOK_COUNT);
    }

    @DisplayName("возвращать ожидаемую книгу по id")
    @Test
    void shouldReturnExpectedBook() {

        Optional<Book> book = Optional.ofNullable(bookRepository.findById(DEFAULT_BOOK_ID));
        assertThat(book).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("title", OLD_BOOK_TITLE);
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBooksList() {

        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        List<Book> books = bookRepository.findAll();
        assertThat(books).isNotNull().hasSize(EXPECTED_BOOK_COUNT)
                .allMatch(s -> !s.getTitle().equals(""));
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);

        assertThat(books).extracting(Book::getId, Book::getTitle)
                .containsExactlyInAnyOrder(
                        tuple(1L, "Евгений Онегин"),
                        tuple(2L, "Час Быка")
                );
    }

    @DisplayName("корректно сохранять книгу")
    @Test
    void shouldSaveAllBookInfo() {

        Author author = em.find(Author.class, NEW_BOOK_AUTHORID);
        Genre genre = em.find(Genre.class, NEW_BOOK_GENREID);

        Book book = new Book(0, NEW_BOOK_TITLE, author, genre);
        bookRepository.save(book);
        assertThat(book.getId()).isGreaterThan(0);

        Book actualBook = em.find(Book.class, book.getId());
        assertThat(actualBook).isNotNull().matches(s -> s.getTitle().equals(NEW_BOOK_TITLE));
        assertThat(actualBook.getGenre().getName()).isEqualTo(genre.getName());
        assertThat(actualBook.getAuthor().getFirstName()).isEqualTo(author.getFirstName());
        assertThat(actualBook.getAuthor().getLastName()).isEqualTo(author.getLastName());
    }

    @DisplayName("удалять книгу по id")
    @Test
    void shouldCorrectDeleteBookById() {

        val book = em.find(Book.class, DEFAULT_BOOK_ID);
        assertThat(book).isNotNull();
        em.detach(book);

        bookRepository.deleteById(DEFAULT_BOOK_ID);
        val deletedBook = em.find(Book.class, DEFAULT_BOOK_ID);

        assertThat(deletedBook).isNull();
    }

    @DisplayName("возвращать книги по жанру")
    @Test
    void shouldReturnExpectedBookByGenre() {

        Author author = new Author(0, NEW_AUTHOR_FIRSTNAME, NEW_AUTHOR_LASTNAME, null);
        Genre genre = new Genre(0, NEW_GENRE_NAME,null);
        Book book = new Book(0, NEW_BOOK_TITLE, author, genre);
        em.persist(book);
        List<Book> actualListBook = bookRepository.findAllByGenreName(NEW_GENRE_NAME);
        actualListBook.forEach(a ->assertThat(a.getGenre().getName()).isEqualTo(NEW_GENRE_NAME));
    }

    @DisplayName("возвращать книги по фамилии автора")
    @Test
    void shouldReturnExpectedBookByAuthorLastName() {

        Author author = new Author(0, NEW_AUTHOR_FIRSTNAME, NEW_AUTHOR_LASTNAME, null);
        Genre genre = new Genre(0, NEW_GENRE_NAME,null);
        Book book = new Book(0, NEW_BOOK_TITLE, author, genre);
        em.persist(book);
        List<Book> actualListBook = bookRepository.findAllByAuthorLastName(NEW_AUTHOR_LASTNAME);
        actualListBook.forEach(a ->assertThat(a.getAuthor().getLastName()).isEqualTo(NEW_AUTHOR_LASTNAME));
    }
}