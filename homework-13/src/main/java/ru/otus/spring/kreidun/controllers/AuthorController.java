package ru.otus.spring.kreidun.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.kreidun.models.Author;
import ru.otus.spring.kreidun.repositories.AuthorRepository;

import java.util.List;

@Controller
public class AuthorController {

    private final AuthorRepository repository;

    @Autowired
    public AuthorController(AuthorRepository repository) {

        this.repository = repository;
    }

    @GetMapping("/authors")
    public String getListAuthors(Model model) {

        List<Author> authors = repository.findAll();
        model.addAttribute("authors", authors);
        return "listAuthors";
    }
}
