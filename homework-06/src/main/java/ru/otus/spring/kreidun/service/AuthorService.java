package ru.otus.spring.kreidun.service;

public interface AuthorService {

    void addNewAuthor(String firstName, String lastName);
    void showAllAuthors();
}
