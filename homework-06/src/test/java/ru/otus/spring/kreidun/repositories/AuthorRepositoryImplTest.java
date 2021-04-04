package ru.otus.spring.kreidun.repositories;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.kreidun.models.Author;

import java.text.ParseException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository с авторами должен")
@DataJpaTest
@Import(AuthorRepositoryImpl.class)
class AuthorRepositoryImplTest {

    private static final int EXPECTED_AUTHORS_COUNT = 2;
    private static final String NEW_AUTHOR_FIRSTNAME = "ИМЯ";
    private static final String NEW_AUTHOR_LASTNAME = "ФАМИЛИЯ";
    private static final String OLD_AUTHOR_FIRSTNAME = "Александр";
    private static final String OLD_AUTHOR_LASTNAME = "Пушкин";
    private static final long DEFAULT_AUTHOR_ID = 1;
    private static final int EXPECTED_QUERIES_COUNT = 1;

    @Autowired
    private AuthorRepositoryImpl authorRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемое количество авторов")
    @Test
    void shouldReturnExpectedAuthorCount() {
        assertThat(authorRepository.count()).isEqualTo(EXPECTED_AUTHORS_COUNT);
    }


    @DisplayName("загружать информацию о нужном авторе по его id")
    @Test
    void shouldFindExpectedAuthorById() {

        Optional<Author> author = Optional.ofNullable(authorRepository.findById(DEFAULT_AUTHOR_ID));
        assertThat(author).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("lastName", OLD_AUTHOR_LASTNAME);
    }

    @DisplayName("загружать список всех авторов")
    @Test
    void shouldReturnCorrectAuthorList() {

        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        val authors = authorRepository.getAll();
        assertThat(authors).isNotNull().hasSize(EXPECTED_AUTHORS_COUNT).allMatch(s -> !s.getFirstName().equals(""));
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
    }

    @DisplayName("корректно сохранять информацию об авторе")
    @Test
    void shouldSaveAllAuthorInfo() throws ParseException {

        Author author = new Author(0, NEW_AUTHOR_FIRSTNAME, NEW_AUTHOR_LASTNAME,null);
        authorRepository.save(author);
        assertThat(author.getId()).isGreaterThan(0);

        Author actualAuthor = em.find(Author.class, author.getId());
        assertThat(actualAuthor).isNotNull().matches(s -> s.getFirstName().equals(NEW_AUTHOR_FIRSTNAME));
        assertThat(actualAuthor).isNotNull().matches(s -> s.getLastName().equals(NEW_AUTHOR_LASTNAME));
    }
}