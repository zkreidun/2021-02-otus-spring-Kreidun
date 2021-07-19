package ru.otus.spring.kreidun.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.kreidun.models.Author;
import ru.otus.spring.kreidun.services.AuthorService;

import java.util.List;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {

        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String getListAuthors(Model model) {

        List<Author> authors = authorService.getAll();
        model.addAttribute("authors", authors);
        return "listAuthors";
    }
}
