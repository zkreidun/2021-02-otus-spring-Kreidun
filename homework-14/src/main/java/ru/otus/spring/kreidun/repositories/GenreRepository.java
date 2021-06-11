package ru.otus.spring.kreidun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.kreidun.models.GenreForWrite;

public interface GenreRepository extends JpaRepository<GenreForWrite, String> {

    GenreForWrite findByName(String name);
}
