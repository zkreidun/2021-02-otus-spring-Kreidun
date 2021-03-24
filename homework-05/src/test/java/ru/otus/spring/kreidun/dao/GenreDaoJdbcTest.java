package ru.otus.spring.kreidun.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.kreidun.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@DisplayName("Dao с жанрами должно")
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    private static final int EXPECTED_GENRES_COUNT = 3;
    private static final int NEW_GENRE_ID = 4;
    private static final String NEW_GENRE_NAME = "Новый жанр";
    private static final String OLD_GENRE_NAME = "Роман в стихах";
    private static final long DEFAULT_GENRE_ID = 1;

    @Autowired
    private GenreDaoJdbc genreDao;

    @DisplayName("возвращать ожидаемое количество жанров")
    @Test
    void shouldReturnExpectedGenreCount() {
        assertThat(genreDao.count()).isEqualTo(EXPECTED_GENRES_COUNT);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("добавлять жанр")
    @Test
    void shouldInsertGenre() {

        Genre expectedGenre = new Genre(NEW_GENRE_ID, NEW_GENRE_NAME);
        long newId = genreDao.insert(NEW_GENRE_NAME);
        Genre actualGenre = genreDao.getById(newId);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("возвращать ожидаемый жанр по id")
    @Test
    void shouldReturnExpectedGenre() {

        Genre expectedGenre = new Genre(DEFAULT_GENRE_ID, OLD_GENRE_NAME);
        Genre actualGenre = genreDao.getById(DEFAULT_GENRE_ID);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void shouldReturnExpectedGenresList() {

        assertThat(genreDao.getAll()).extracting(Genre::getId, Genre::getName)
                .containsExactlyInAnyOrder(
                        tuple(1L, "Роман в стихах"),
                        tuple(2L, "Фантастика"),
                        tuple(3L, "Детектив")
                );
    }
}