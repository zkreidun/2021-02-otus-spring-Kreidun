package ru.otus.spring.kreidun.services;

import ru.otus.spring.kreidun.models.Book;

public interface AclMyService {
    void addPermission(Book book);
    void deletePermission(Book book);
}
