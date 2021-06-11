package ru.otus.spring.kreidun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.kreidun.models.CommentForWrite;

public interface CommentRepository extends JpaRepository<CommentForWrite, String>
{
}