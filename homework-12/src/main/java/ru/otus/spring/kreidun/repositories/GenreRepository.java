package ru.otus.spring.kreidun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.kreidun.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
