package ru.otus.spring.kreidun.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.kreidun.models.Author;
import ru.otus.spring.kreidun.models.Book;
import ru.otus.spring.kreidun.models.Genre;
import ru.otus.spring.kreidun.services.AuthorService;
import ru.otus.spring.kreidun.services.BookService;
import ru.otus.spring.kreidun.services.CommentService;
import ru.otus.spring.kreidun.services.GenreService;

import java.util.List;

@Controller
public class BookController {

    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService, GenreService genreService,
                          AuthorService authorService, CommentService commentService) {
        this.bookService = bookService;
        this.genreService = genreService;
        this.authorService = authorService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String getListBook(Model model) {

        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        return "listBooks";
    }

    @GetMapping("/editbook")
    public String getEditBook(@RequestParam("id") long id, Model model) {

        Book book = bookService.findBookById(id).orElseThrow(RuntimeException::new);
        model.addAttribute("book", book);
        List<Author> authors = authorService.getAll();
        model.addAttribute("allauthors", authors);
        List<Genre> genres = genreService.getAll();
        model.addAttribute("allgenres", genres);
        return "editBook";
    }


    @GetMapping("/addbook")
    public String getAddBook( Model model) {

        Book book = new Book();
        model.addAttribute("book", book);
        List<Author> authors = authorService.getAll();
        model.addAttribute("allauthors", authors);
        List<Genre> genres = genreService.getAll();
        model.addAttribute("allgenres", genres);
        return "editBook";
    }

    @Transactional
    @PostMapping("/deletebook")
    public String postDeleteBook(@RequestParam("id") long id) {

        commentService.deleteAllCommentsByBookId(id);
        bookService.deleteBookById(id);
        return "redirect:/";
    }


    @PostMapping("/saveBook")
    public String postSaveBook(
            @RequestParam("id") long id,
            @RequestParam("title") String title,
            @RequestParam("author") Author author,
            @RequestParam("genre") Genre genre
    ) {
        Book book;

        if (id!=0) {
            book = bookService.findBookById(id).orElseThrow(RuntimeException::new);
            book.setTitle(title);
            book.setAuthor(author);
            book.setGenre(genre);

        } else
        {
            book = new Book(title, author, genre);
        }

        bookService.saveBook(book);
        return "redirect:/";
    }
}
