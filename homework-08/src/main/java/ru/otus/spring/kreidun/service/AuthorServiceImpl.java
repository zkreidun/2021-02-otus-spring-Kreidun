package ru.otus.spring.kreidun.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.kreidun.repositories.AuthorRepository;
import ru.otus.spring.kreidun.models.Author;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final IOService ioService;

    @Override
    public void add(String firstName, String lastName) {

        Author author = new Author(firstName, lastName);
        authorRepository.save(author);
    }

    @Override
    public void showAll() {

        String showAuthor;
        List<Author> listAuthor = authorRepository.findAll();
        for (Author author : listAuthor) {
            showAuthor = "Id: = " + author.getId() + " FirstName: " + author.getFirstName() +
                    " LastName: " + author.getLastName();
            ioService.printString(showAuthor);
        }
    }
}
