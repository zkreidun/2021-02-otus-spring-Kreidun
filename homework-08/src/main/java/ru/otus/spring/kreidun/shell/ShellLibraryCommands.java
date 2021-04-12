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
        authorService.showAll();
    }

    @SneakyThrows
    @ShellMethod(key = {"g", "genres"}, value = "show all genres")
    public void showAllGenres() {
        ioService.printString("Все жанры:");
        genreService.showAll();
    }

    @SneakyThrows
    @ShellMethod(key = {"b", "books"}, value = "show all books")
    public void showAllBooks() {
        ioService.printString("Все книги:");
        bookService.showAll();
    }

    @SneakyThrows
    @ShellMethod(key = {"bai", "booksbyauthorid"}, value = "show books by author_id")
    public void showBooksByAuthorId(String author_id) {
        ioService.printString("Книги автора:");
        bookService.showByAuthorId(author_id);
    }

    @SneakyThrows
    @ShellMethod(key = {"c", "comments"}, value = "show all comments")
    public void showAllComments() {
        ioService.printString("Все комментарии:");
        commentService.showAll();
    }


    @SneakyThrows
    @ShellMethod(key = {"aa", "addauthor"}, value = "add author")
    public void addAuthor(String firstName, String lastName) {

        authorService.add(firstName, lastName);
        authorService.showAll();
    }

    @SneakyThrows
    @ShellMethod(key = {"ag", "addgenre"}, value = "add genre")
    public void addGenre(String genreName) {

        genreService.add(genreName);
        genreService.showAll();
    }

    @SneakyThrows
    @ShellMethod(key = {"ab", "addbook"}, value = "add book")
    public void addBook(String bookTitle,
                        String authorId,
                        String genreId) {

        boolean result = bookService.add(bookTitle, authorId, genreId);
        if (result)
            bookService.showAll();
    }

    @SneakyThrows
    @ShellMethod(key = {"ac", "addcomment"}, value = "add comment")
    public void addComment(String commentName, String bookId) {

        commentService.add(commentName, bookId);
        commentService.showAll();
    }

    @SneakyThrows
    @ShellMethod(key = {"db", "delbook"}, value = "delete book")
    public void delBook(String id) {

        bookService.del(id);
        bookService.showAll();
    }

    @SneakyThrows
    @ShellMethod(key = {"dc", "delcomment"}, value = "delete comment")
    public void delComment(String id) {

        commentService.del(id);
        commentService.showAll();
    }

    @SneakyThrows
    @ShellMethod(key = {"ub", "updbook"}, value = "update book")
    public void updBook(String Id, String bookTitle, String authorId, String genreId) {

        bookService.upd(Id, bookTitle, authorId, genreId);
        bookService.showAll();
    }

    @SneakyThrows
    @ShellMethod(key = {"uc", "updcomment"}, value = "update comment")
    public void updComment(String id, String text, String bookId) {

        commentService.upd(id, text, bookId);
        commentService.showAll();
    }

}
