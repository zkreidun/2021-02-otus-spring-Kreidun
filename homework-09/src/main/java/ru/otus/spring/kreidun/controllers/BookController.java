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
import ru.otus.spring.kreidun.repositories.AuthorRepository;
import ru.otus.spring.kreidun.repositories.BookRepository;
import ru.otus.spring.kreidun.repositories.CommentRepository;
import ru.otus.spring.kreidun.repositories.GenreRepository;

import java.util.List;

@Controller
public class BookController {

    private final BookRepository repository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public BookController(BookRepository repository, GenreRepository genreRepository,
                          AuthorRepository authorRepository, CommentRepository commentRepository) {

        this.repository = repository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/")
    public String getListBook(Model model) {

        List<Book> books = repository.findAll();
        model.addAttribute("books", books);
        return "listBooks";
    }

    @GetMapping("/editbook")
    public String getEditBook(@RequestParam("id") long id, Model model) {

        Book book = repository.findById(id);
        model.addAttribute("book", book);
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("allauthors", authors);
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("allgenres", genres);
        return "editBook";
    }


    @GetMapping("/addbook")
    public String getAddBook( Model model) {

        Book book = new Book();
        model.addAttribute("book", book);
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("allauthors", authors);
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("allgenres", genres);
        return "editBook";
    }

    @Transactional
    @PostMapping("/deletebook")
    public String postDeleteBook(@RequestParam("id") long id) {

        commentRepository.deleteAllByBookId(id);
        repository.deleteById(id);
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
            book = repository.findById(id);
            book.setTitle(title);
            book.setAuthor(author);
            book.setGenre(genre);

        } else
        {
            book = new Book(title, author, genre);
        }

        repository.save(book);
        return "redirect:/";
    }
}
