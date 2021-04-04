package ru.otus.spring.kreidun.repositories;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.kreidun.models.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@DisplayName("Repository с жанрами должно")
@DataJpaTest
@Import(GenreRepositoryImpl.class)
class GenreRepositoryImplTest {

    private static final int EXPECTED_GENRES_COUNT = 3;
    private static final int NEW_GENRE_ID = 4;
    private static final String NEW_GENRE_NAME = "Новый жанр";
    private static final String OLD_GENRE_NAME = "Роман в стихах";
    private static final long DEFAULT_GENRE_ID = 1;
    private static final int EXPECTED_QUERIES_COUNT = 1;

    @Autowired
    private GenreRepositoryImpl genreRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемое количество жанров")
    @Test
    void shouldReturnExpectedGenreCount() {
        assertThat(genreRepository.count()).isEqualTo(EXPECTED_GENRES_COUNT);
    }

    @DisplayName("возвращать ожидаемый жанр по id")
    @Test
    void shouldReturnExpectedGenre() {

        Optional<Genre> genre = Optional.ofNullable(genreRepository.findById(DEFAULT_GENRE_ID));
        assertThat(genre).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("name", OLD_GENRE_NAME);
    }

    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void shouldReturnExpectedGenresList() {

        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        val genres = genreRepository.getAll();
        assertThat(genres).isNotNull().hasSize(EXPECTED_GENRES_COUNT).allMatch(s -> !s.getName().equals(""));
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);

        assertThat(genres).extracting(Genre::getId, Genre::getName)
                .containsExactlyInAnyOrder(
                        tuple(1L, "Роман в стихах"),
                        tuple(2L, "Фантастика"),
                        tuple(3L, "Детектив")
                );
    }

    @DisplayName("корректно сохранять информацию о жанре")
    @Test
    void shouldSaveAllGenreInfo()  {

        Genre genre = new Genre(0, NEW_GENRE_NAME, null);
        genreRepository.save(genre);
        assertThat(genre.getId()).isGreaterThan(0);

        Genre actualGenre = em.find(Genre.class, genre.getId());
        assertThat(actualGenre).isNotNull().matches(s -> s.getName().equals(NEW_GENRE_NAME));
    }
}