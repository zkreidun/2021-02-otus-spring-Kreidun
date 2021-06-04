package ru.otus.spring.kreidun.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.kreidun.models.Genre;
import ru.otus.spring.kreidun.repositories.GenreRepository;

import java.util.List;

@Controller
public class GenreController {

    private final GenreRepository repository;

    @Autowired
    public GenreController(GenreRepository repository) {

        this.repository = repository;
    }

    @GetMapping("/genres")
    public String getListGenres(Model model) {

        List<Genre> genres = repository.findAll();
        model.addAttribute("genres", genres);
        return "listGenres";
    }
}
