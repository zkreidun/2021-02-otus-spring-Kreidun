package ru.otus.spring.kreidun.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.kreidun.repositories.AuthorRepository;
import ru.otus.spring.kreidun.repositories.BookRepository;
import ru.otus.spring.kreidun.repositories.CommentRepository;
import ru.otus.spring.kreidun.repositories.GenreRepository;
import ru.otus.spring.kreidun.models.Author;
import ru.otus.spring.kreidun.models.Book;
import ru.otus.spring.kreidun.models.Genre;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;
    private final IOService ioService;

    @Override
    public boolean add(String bookName, String authorId, String genreId) {

        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (authorOptional.isEmpty()) {
            ioService.printString("Автор не найден!");
            return false;
        }
        Author author = authorOptional.get();

        Optional<Genre> genreOptional = genreRepository.findById(genreId);
        if (genreOptional.isEmpty()) {
            ioService.printString("Жанр не найден!");
            return false;
        }
        Genre genre = genreOptional.get();

        Book book = new Book(bookName, author, genre);
        bookRepository.save(book);
        return true;
    }

    @Override
    public boolean del(String id) {

        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            ioService.printString("Книга не найдена!");
            return false;
        }

        commentRepository.deleteByBookId(id);
        bookRepository.deleteById(id);

        return true;
    }

    @Override
    public boolean upd(String id, String bookTitle, String authorId, String genreId){

        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            ioService.printString("Книга не найдена!");
            return false;
        }
        Book book = bookOptional.get();

        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (authorOptional.isEmpty()) {
            ioService.printString("Автор не найден!");
            return false;
        }
        Author author = authorOptional.get();

        Optional<Genre> genreOptional = genreRepository.findById(genreId);
        if (genreOptional.isEmpty()) {
            ioService.printString("Жанр не найден!");
            return false;
        }
        Genre genre = genreOptional.get();

        book.setTitle(bookTitle);
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepository.save(book);
        return true;
    }

    @Override
    public void showAll() {

        String showBook;
        List<Book> listBook = bookRepository.findAll();
        for (Book book : listBook) {
            showBook = "Id: = " + book.getId() + " BookTitle: " + book.getTitle() +
                    " Author: " + book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName() +
                    " Genre: " + book.getGenre().getName();
            ioService.printString(showBook);
        }
    }

    @Override
    public void showByAuthorId(String authorId) {

        String showBook;
        List<Book> listBook = bookRepository.findAllByAuthorId(authorId);

        for (Book book : listBook) {
            showBook = "Id: = " + book.getId() + " BookTitle: " + book.getTitle() +
                    " Author: " + book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName() +
                    " Genre: " + book.getGenre().getName();
            ioService.printString(showBook);
        }
    }
}
