package ru.otus.spring.kreidun.service;

public interface LibraryService {

    void addNewGenre(String genreName);

    void addNewAuthor(String firstName, String lastName);

    boolean addNewBook(String bookTitle, Long authorId, Long genreId);

    void delBook(long id);

    void updBook(long id, String bookTitle, long authorId, long genreId);

    void showAllGenres();

    void showAllAuthors();

    void showAllBooks();
}
