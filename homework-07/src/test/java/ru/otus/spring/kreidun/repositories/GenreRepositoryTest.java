package ru.otus.spring.kreidun.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.kreidun.models.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository с жанрами должно")
@DataJpaTest
class GenreRepositoryTest {

    private static final String NEW_GENRE_NAME = "Новый жанр";

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("возвращать ожидаемый жанр по Id")
    @Test
    void shouldReturnExpectedGenreById() {

        Genre genre = new Genre(0, NEW_GENRE_NAME,null);
        em.persist(genre);
        Genre genreForCompare = genreRepository.findById(genre.getId());
        assertThat(genreForCompare.getName()).isEqualTo(NEW_GENRE_NAME);
    }


    @DisplayName("возвращать ожидаемый жанр по Name")
    @Test
    void shouldReturnExpectedGenreByName() {

        Genre genre = new Genre(0, NEW_GENRE_NAME,null);
        em.persist(genre);
        Genre genreForCompare = genreRepository.findByName(NEW_GENRE_NAME);
        assertThat(genreForCompare.getName()).isEqualTo(NEW_GENRE_NAME);
    }
}