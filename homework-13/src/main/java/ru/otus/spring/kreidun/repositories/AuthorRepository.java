package ru.otus.spring.kreidun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.kreidun.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>
{
}
