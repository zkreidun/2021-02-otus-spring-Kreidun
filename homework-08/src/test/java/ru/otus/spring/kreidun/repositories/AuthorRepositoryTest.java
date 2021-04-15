package ru.otus.spring.kreidun.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.kreidun.models.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository с авторами должен")
@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.spring.kreidun.config", "ru.otus.spring.kreidun.repositories"})
class AuthorRepositoryTest {

    private static final String OLD_AUTHOR_LASTNAME = "Пушкин";

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void findByLastName() {

        List<Author> actualLisAuthor = authorRepository.findByLastName(OLD_AUTHOR_LASTNAME);
        actualLisAuthor.forEach(a ->assertThat(a.getLastName()).isEqualTo(OLD_AUTHOR_LASTNAME));
    }
}