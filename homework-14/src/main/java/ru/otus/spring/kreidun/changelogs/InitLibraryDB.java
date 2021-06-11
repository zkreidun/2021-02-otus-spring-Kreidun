package ru.otus.spring.kreidun.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.kreidun.models.Author;
import ru.otus.spring.kreidun.models.Book;
import ru.otus.spring.kreidun.models.Comment;
import ru.otus.spring.kreidun.models.Genre;

import java.text.ParseException;

@ChangeLog(order = "001")
public class InitLibraryDB {

        private Genre genre1;
        private Genre genre2;
        private Book book1;
        private Book book2;
        private Author author1;
        private Author author2;

        @ChangeSet(order = "000", id = "dropDB", author = "author", runAlways = true)
        public void dropDB(MongoDatabase database) {
            database.drop();
        }

        @ChangeSet(order = "001", id = "initGenres", author = "author", runAlways = true)
        public void initGenres(MongoTemplate template) {

            genre1 = template.save(new Genre("Роман в стихах"));
            genre2 = template.save(new Genre("Фантастика"));
            template.save(new Genre("Детектив"));
        }

        @ChangeSet(order = "002", id = "initAuthors", author = "author", runAlways = true)
        public void initSAuthors(MongoTemplate template) throws ParseException {

            author1 = template.save(new Author("Александр", "Пушкин"));
            author2 = template.save(new Author("Иван", "Ефремов"));
        }

        @ChangeSet(order = "003", id = "initBooks", author = "author", runAlways = true)
        public void initBooks(MongoTemplate template) {

            book1 = template.save(new Book("Евгений Онегин", author1, genre1));
            book2 = template.save(new Book("Час Быка", author2, genre2));
        }

        @ChangeSet(order = "004", id = "initComments", author = "author", runAlways = true)
        public void initComments(MongoTemplate template) {

            template.save(new Comment("Превосходно", book1));
            template.save(new Comment ("Увлекательно", book1));
            template.save(new Comment ("Не читал", book2));
        }
}
