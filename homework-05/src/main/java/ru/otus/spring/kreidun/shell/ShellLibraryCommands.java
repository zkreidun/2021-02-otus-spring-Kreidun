package ru.otus.spring.kreidun.shell;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.kreidun.domain.Author;
import ru.otus.spring.kreidun.domain.Book;
import ru.otus.spring.kreidun.domain.Genre;
import ru.otus.spring.kreidun.service.IOService;
import ru.otus.spring.kreidun.service.LibraryService;


@ShellComponent
@RequiredArgsConstructor
public class ShellLibraryCommands {

    private final LibraryService libraryService;
    private final IOService ioService;
    private Author author;
    private Genre genre;
    private Book book;

    @SneakyThrows
    @ShellMethod(key = {"a", "authors"}, value = "show all authors")
    public void showAllAuthors() {
        ioService.printString("Все авторы:");
        libraryService.showAllAuthors();
    }

    @SneakyThrows
    @ShellMethod(key = {"g", "genres"}, value = "show all genres")
    public void showAllGenres() {
        ioService.printString("Все жанры:");
        libraryService.showAllGenres();
    }

    @SneakyThrows
    @ShellMethod(key = {"b", "books"}, value = "show all books")
    public void showAllBooks() {
        ioService.printString("Все книги:");
        libraryService.showAllBooks();
    }


    @SneakyThrows
    @ShellMethod(key = {"aa", "addauthor"}, value = "add author")
    public void addAuthor(String firstName, String lastName) {

        libraryService.addNewAuthor(firstName, lastName);
        libraryService.showAllAuthors();
    }

    @SneakyThrows
    @ShellMethod(key = {"ag", "addgenre"}, value = "add genre")
    public void addGenre(String genreName) {

        libraryService.addNewGenre(genreName);
        libraryService.showAllGenres();
    }

    @SneakyThrows
    @ShellMethod(key = {"ab", "addbook"}, value = "add book")
    public void addBook(String bookTitle,
                        long authorId,
                        long genreId) {
        boolean result = libraryService.addNewBook(bookTitle, authorId, genreId);
        if (result)
            libraryService.showAllBooks();
    }

    @SneakyThrows
    @ShellMethod(key = {"db", "delbook"}, value = "delete book")
    public void delBook(long Id) {
        libraryService.delBook(Id);
        libraryService.showAllBooks();
    }

    @SneakyThrows
    @ShellMethod(key = {"ub", "updbook"}, value = "update book")
    public void updBook(long Id, String bookTitle, long authorId, long genreId) {
        libraryService.updBook(Id, bookTitle, authorId, genreId);
        libraryService.showAllBooks();
    }
}
