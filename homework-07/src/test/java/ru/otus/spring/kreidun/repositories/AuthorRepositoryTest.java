package ru.otus.spring.kreidun.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.kreidun.models.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository с авторами должен")
@DataJpaTest
class AuthorRepositoryTest {

    private static final String NEW_AUTHOR_FIRSTNAME = "ИМЯ";
    private static final String NEW_AUTHOR_LASTNAME = "ФАМИЛИЯ";

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("загружать информацию об авторе по его id")
    @Test
    void shouldFindExpectedAuthorById() {

        Author author = new Author(0, NEW_AUTHOR_FIRSTNAME, NEW_AUTHOR_LASTNAME, null);
        em.persist(author);
        Author authorForCompare = authorRepository.findById(author.getId());
        assertThat(authorForCompare.getFirstName()).isEqualTo(NEW_AUTHOR_FIRSTNAME);
        assertThat(authorForCompare.getLastName()).isEqualTo(NEW_AUTHOR_LASTNAME);
    }

    @DisplayName("загружать информацию об авторе по его LastName")
    @Test
    void shouldFindExpectedAuthorByLastName() {

        Author author = new Author(0, NEW_AUTHOR_FIRSTNAME, NEW_AUTHOR_LASTNAME, null);
        em.persist(author);
        List<Author> authorList = authorRepository.findByLastName(NEW_AUTHOR_LASTNAME);
        authorList.forEach(a ->assertThat(a.getLastName()).isEqualTo(NEW_AUTHOR_LASTNAME));
    }
}