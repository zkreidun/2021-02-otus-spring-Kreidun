package ru.otus.spring.kreidun.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.kreidun.dao.AuthorDao;
import ru.otus.spring.kreidun.dao.BookDao;
import ru.otus.spring.kreidun.dao.GenreDao;
import ru.otus.spring.kreidun.domain.Author;
import ru.otus.spring.kreidun.domain.Book;
import ru.otus.spring.kreidun.domain.Genre;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final AuthorDao authorDao;
    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final IOService ioService;

    public LibraryServiceImpl(AuthorDao authorDao, BookDao bookDao, GenreDao genreDao, IOService ioService) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
        this.genreDao = genreDao;
        this.ioService = ioService;
    }

    @Override
    public void addNewGenre(String genreName) {
        genreDao.insert(genreName);
    }

    @Override
    public void addNewAuthor(String firstName, String lastName) {
        authorDao.insert(firstName, lastName);
    }

    @Override
    public boolean addNewBook(String bookTitle, Long authorId, Long genreId) {

        Author author = authorDao.getById(authorId);
        if (author == null)
        {
            ioService.printString("Данный автор не добавлен в библиотеку!");
            return false;
        }

        Genre genre = genreDao.getById(genreId);
        if (genre == null)
        {
            ioService.printString("Данный жанр не добавлен в библиотеку!");
            return false;
        }

        bookDao.insert(bookTitle, authorId, genreId);
        return true;
    }

    @Override
    public void delBook(long id){
        bookDao.deleteById(id);
    }

    @Override
    public void updBook(long id, String bookTitle, long authorId, long genreId){
        bookDao.update(id, bookTitle, authorId, genreId);
    }

    @Override
    public void showAllGenres() {

        String showGenre;
        List<Genre> listGenres = genreDao.getAll();
        for (Genre genre : listGenres) {
            showGenre = "Id: = " + genre.getId() + " Name: " + genre.getName();
            ioService.printString(showGenre);
        }
    }

    @Override
    public void showAllAuthors() {

        String showAuthor;
        List<Author> listAuthor = authorDao.getAll();
        for (Author author : listAuthor) {
            showAuthor = "Id: = " + author.getId() + " FirstName: " + author.getFirstName() +
                    " LastName: " + author.getLastName();
            ioService.printString(showAuthor);
        }
    }

    @Override
    public void showAllBooks() {

        String showBook;
        List<Book> listBook = bookDao.getAll();
        for (Book book : listBook) {
            showBook = "Id: = " + book.getId() + " BookTitle: " + book.getTitle() +
                    " Author: " + book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName() +
                    " Genre: " + book.getGenre().getName();
            ioService.printString(showBook);
        }
    }
}
