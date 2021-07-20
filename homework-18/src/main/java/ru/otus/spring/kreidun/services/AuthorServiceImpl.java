package ru.otus.spring.kreidun.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.kreidun.models.Author;
import ru.otus.spring.kreidun.repositories.AuthorRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static ru.otus.spring.kreidun.utils.Util.getRandomSleep;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    @HystrixCommand(groupKey = "AuthorGroup", commandKey = "getAllAuthorsCommand",
            fallbackMethod = "getReserveListAuthors")
    @Override
    public List<Author> getAll() {
        getRandomSleep();
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public List<Author> getReserveListAuthors( ) {
        return Collections.emptyList();
    }

    @Override
    @HystrixCommand(groupKey = "AuthorGroup", commandKey = "getAuthorByLastName",
            fallbackMethod = "getReserveListAuthors")
    public List<Author> findAuthorByLastName(String lastName) {
        return authorRepository.findByLastName(lastName);
    }
}
