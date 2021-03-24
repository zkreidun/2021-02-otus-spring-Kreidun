package ru.otus.spring.kreidun.domain;

public class Book {

    private final long id;
    private final String title;
    private final Author author;
    private final Genre genre;

    public Book(long id, String title, Author author, Genre genre){
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public String getTitle() { return title; }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public long getId() {
        return id;
    }


}
