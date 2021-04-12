package ru.otus.spring.kreidun.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.kreidun.models.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@DisplayName("Repository с книгами должен")
@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.spring.kreidun.config", "ru.otus.spring.kreidun.repositories"})
class BookRepositoryTest {

    private static final int EXPECTED_BOOK_COUNT = 2;
    private static final String OLD_AUTHOR_LASTNAME = "Пушкин";

    @Autowired
    private  BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @DisplayName("возвращать ожидаемое количество книг")
    @Test
    void shouldReturnExpectedBookCount() {
        assertThat(bookRepository.count()).isEqualTo(EXPECTED_BOOK_COUNT);
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBooksList() {

        List<Book> books = bookRepository.findAll();
        assertThat(books).extracting(Book::getTitle)
                .containsOnlyOnce("Евгений Онегин","Час Быка");
    }

    @DisplayName("возвращать книги по фамилии автора")
    @Test
    void shouldReturnExpectedBookByAuthorLastName() {

        List<Book> actualListBook = bookRepository.findAllByAuthorId(authorRepository.findByLastName(OLD_AUTHOR_LASTNAME).get(0).getId());
        actualListBook.forEach(a ->assertThat(a.getAuthor().getLastName()).isEqualTo(OLD_AUTHOR_LASTNAME));
    }

    @DisplayName("при удалении автора не должен удалять книги автора")
    @Test
    void shouldLeaveAuthorsBooksWhenRemovingAuthor() {

        val authors = authorRepository.findByLastName("Ефремов");
        val author = authors.get(0);
        if (author != null) {
            String authorId = author.getId();
            List<Book> listBooks = bookRepository.findAllByAuthorId(authorId);
            val expectedBooksArrayLength = 0;
            authorRepository.delete(author);
            // Загружаем книги автора заново
            List<Book> listActualBooks  =  bookRepository.findAllByAuthorId(authorId);
            val actualBooksArrayLength = listActualBooks.size();
            assertThat(actualBooksArrayLength).isNotEqualTo(expectedBooksArrayLength);
        } else
        {
            System.out.println("Авторы не найдены!");
        }
    }
}