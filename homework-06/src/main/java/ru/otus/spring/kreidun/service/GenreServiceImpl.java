package ru.otus.spring.kreidun.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.kreidun.repositories.GenreRepository;
import ru.otus.spring.kreidun.models.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final IOService ioService;

    @Override
    public void addNewGenre(String genreName) {

        Genre genre = new Genre(0, genreName,null);
        genreRepository.save(genre);
    }

    @Override
    public void showAllGenres() {

        String showGenre;
        List<Genre> listGenres = genreRepository.getAll();
        for (Genre genre : listGenres) {
            showGenre = "Id: = " + genre.getId() + " Name: " + genre.getName();
            ioService.printString(showGenre);
        }
    }
}
