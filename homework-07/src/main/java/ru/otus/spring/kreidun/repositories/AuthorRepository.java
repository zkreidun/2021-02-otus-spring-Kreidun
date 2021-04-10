package ru.otus.spring.kreidun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.kreidun.models.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long>
{
    Author findById(long id);
    List<Author> findAll();
    List<Author> findByLastName(String lastName);
}
