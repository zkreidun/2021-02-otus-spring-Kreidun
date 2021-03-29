package ru.otus.spring.kreidun.shell;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.kreidun.service.*;


@ShellComponent
@RequiredArgsConstructor
public class ShellLibraryCommands {

    private final GenreServiceImpl genreService;
    private final AuthorServiceImpl authorService;
    private final BookService bookService;
    private final CommentService commentService;
    private final IOService ioService;

    @SneakyThrows
    @ShellMethod(key = {"a", "authors"}, value = "show all authors")
    public void showAllAuthors() {
        ioService.printString("Все авторы:");
        authorService.showAllAuthors();
    }

    @SneakyThrows
    @ShellMethod(key = {"g", "genres"}, value = "show all genres")
    public void showAllGenres() {
        ioService.printString("Все жанры:");
        genreService.showAllGenres();
    }

    @SneakyThrows
    @ShellMethod(key = {"b", "books"}, value = "show all books")
    public void showAllBooks() {
        ioService.printString("Все книги:");
        bookService.showAllBooks();
    }

    @SneakyThrows
    @ShellMethod(key = {"c", "comments"}, value = "show all comments")
    public void showAllComments() {
        ioService.printString("Все комментарии:");
        commentService.showAllComments();
    }


    @SneakyThrows
    @ShellMethod(key = {"aa", "addauthor"}, value = "add author")
    public void addAuthor(String firstName, String lastName) {

        authorService.addNewAuthor(firstName, lastName);
        authorService.showAllAuthors();
    }

    @SneakyThrows
    @ShellMethod(key = {"ag", "addgenre"}, value = "add genre")
    public void addGenre(String genreName) {

        genreService.addNewGenre(genreName);
        genreService.showAllGenres();
    }

    @SneakyThrows
    @ShellMethod(key = {"ab", "addbook"}, value = "add book")
    public void addBook(String bookTitle,
                        long authorId,
                        long genreId) {

        boolean result = bookService.addNewBook(bookTitle, authorId, genreId);
        if (result)
            bookService.showAllBooks();
    }

    @SneakyThrows
    @ShellMethod(key = {"ac", "addcomment"}, value = "add comment")
    public void addComment(String commentName, long bookId) {

        commentService.addNewComment(commentName, bookId);
        commentService.showAllComments();
    }

    @SneakyThrows
    @ShellMethod(key = {"db", "delbook"}, value = "delete book")
    public void delBook(long id) {

        bookService.delBook(id);
        bookService.showAllBooks();
    }

    @SneakyThrows
    @ShellMethod(key = {"dc", "delcomment"}, value = "delete comment")
    public void delComment(long id) {

        commentService.deleteComment(id);
        commentService.showAllComments();
    }

    @SneakyThrows
    @ShellMethod(key = {"ub", "updbook"}, value = "update book")
    public void updBook(long Id, String bookTitle, long authorId, long genreId) {

        bookService.updBook(Id, bookTitle, authorId, genreId);
        bookService.showAllBooks();
    }

    @SneakyThrows
    @ShellMethod(key = {"uc", "updcomment"}, value = "update comment")
    public void updComment(long id, String text, long bookId) {

        commentService.updateComment(id, text, bookId);
        commentService.showAllComments();
    }

}
