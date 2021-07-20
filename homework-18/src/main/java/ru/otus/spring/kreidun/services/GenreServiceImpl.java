package ru.otus.spring.kreidun.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.kreidun.models.Genre;
import ru.otus.spring.kreidun.repositories.GenreRepository;

import java.util.Collections;
import java.util.List;

import static ru.otus.spring.kreidun.utils.Util.getRandomSleep;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    @HystrixCommand(groupKey = "GenreGroup", commandKey = "getAllGenresCommand",
            fallbackMethod = "getReserveListGenres")
    public List<Genre> getAll() {
        getRandomSleep();
        return genreRepository.findAll();
    }

    public List<Genre> getReserveListGenres( ) {
        return Collections.emptyList();
    }

    @Override
    public Genre findGenreByName(String name) {
        return genreRepository.findByName(name);
    }
}
