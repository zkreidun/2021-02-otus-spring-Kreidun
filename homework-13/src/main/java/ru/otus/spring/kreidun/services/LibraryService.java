package ru.otus.spring.kreidun.services;

import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.spring.kreidun.models.Book;

public interface LibraryService {

    @PreAuthorize("hasPermission(#book, 'WRITE')")
    void saveBook(@Param("book") Book book);
    Book addNewBook(Book book);
}
