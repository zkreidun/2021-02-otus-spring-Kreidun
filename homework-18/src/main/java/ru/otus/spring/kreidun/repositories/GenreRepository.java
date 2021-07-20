package ru.otus.spring.kreidun.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.spring.kreidun.models.Genre;

import java.util.List;

@RepositoryRestResource(path = "genre")
public interface GenreRepository extends PagingAndSortingRepository<Genre, Long>
{
    List<Genre> findAll();

    @RestResource(path = "genrenames", rel = "ganrenames")
    Genre findByName(String name);
}
