package ru.otus.spring.kreidun.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.kreidun.models.Book;
import ru.otus.spring.kreidun.repositories.BookRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static ru.otus.spring.kreidun.utils.Util.getRandomSleep;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @HystrixCommand(groupKey = "BookGroup", commandKey = "getAllBooksCommand",
            fallbackMethod = "getReserveListBooks")
    public List<Book> getAll() {
        getRandomSleep();
        return bookRepository.findAll();
    }

    public List<Book>  getReserveListBooks() {
        return Collections.emptyList();
    }

    @Override
    public List<Book> findAllBooksByAuthorId(Long id) {
        return bookRepository.findAllByAuthorId(id);
    }

    @Override
    public List<Book> findAllBooksByAuthorLastName(String lastName) {
        return bookRepository.findAllByAuthorLastName(lastName);
    }

    @Override
    public List<Book> findAllBooksByGenreName(String genreName) {
        return bookRepository.findAllByGenreName(genreName);
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
