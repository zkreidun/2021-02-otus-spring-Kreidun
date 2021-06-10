package ru.otus.spring.kreidun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.kreidun.models.Author;
import ru.otus.spring.kreidun.models.AuthorForWrite;

import java.util.List;

public interface AuthorRepository extends JpaRepository<AuthorForWrite, Long> {

    List<AuthorForWrite> findByLastName(String lastname);
}
