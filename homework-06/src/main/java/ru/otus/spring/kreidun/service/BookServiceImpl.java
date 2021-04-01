package ru.otus.spring.kreidun.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.kreidun.repositories.AuthorRepository;
import ru.otus.spring.kreidun.repositories.BookRepository;
import ru.otus.spring.kreidun.repositories.GenreRepository;
import ru.otus.spring.kreidun.models.Author;
import ru.otus.spring.kreidun.models.Book;
import ru.otus.spring.kreidun.models.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final IOService ioService;

    @Override
    @Transactional
    public boolean add(String bookName, Long authorId, Long genreId) {

        Author author = authorRepository.findById(authorId);
        if (author == null) {
            ioService.printString("Автор не найден!");
            return false;
        }

        Genre genre = genreRepository.findById(genreId);
        if (genre == null) {
            ioService.printString("Жанр не найден!");
            return false;
        }

        Book book = new Book(0, bookName, author, genre);
        bookRepository.save(book);
        return true;
    }

    @Override
    @Transactional
    public boolean del(long id) {

        Book book = bookRepository.getById(id);
        if (book == null) {
            ioService.printString("Книга не найдена!");
            return false;
        }

        bookRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean upd(long id, String bookTitle, long authorId, long genreId){

        Book book = bookRepository.getById(id);
        if (book == null) {
            ioService.printString("Книга не найдена!");
            return false;
        }

        Author author = authorRepository.findById(authorId);
        if (author == null) {
            ioService.printString("Автор не найден!");
            return false;
        }

        Genre genre = genreRepository.findById(genreId);
        if (genre == null) {
            ioService.printString("Жанр не найден!");
            return false;
        }

        book.setTitle(bookTitle);
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepository.save(book);
        return true;
    }

    @Override
    public void showAll() {

        String showBook;
        List<Book> listBook = bookRepository.getAll();
        for (Book book : listBook) {
            showBook = "Id: = " + book.getId() + " BookTitle: " + book.getTitle() +
                    " Author: " + book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName() +
                    " Genre: " + book.getGenre().getName();
            ioService.printString(showBook);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void showByAuthorId(long author_id) {

        String showBook;
        List<Book> listBook = authorRepository.findById(author_id).getBooks();
        for (Book book : listBook) {
            showBook = "Id: = " + book.getId() + " BookTitle: " + book.getTitle() +
                    " Author: " + book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName() +
                    " Genre: " + book.getGenre().getName();
            ioService.printString(showBook);
        }
    }
}
