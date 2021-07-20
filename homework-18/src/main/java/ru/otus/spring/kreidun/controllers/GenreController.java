package ru.otus.spring.kreidun.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.kreidun.models.Genre;
import ru.otus.spring.kreidun.services.GenreService;

import java.util.List;

@Controller
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres")
    public String getListGenres(Model model) {

        List<Genre> genres = genreService.getAll();
        model.addAttribute("genres", genres);
        return "listGenres";
    }
}
