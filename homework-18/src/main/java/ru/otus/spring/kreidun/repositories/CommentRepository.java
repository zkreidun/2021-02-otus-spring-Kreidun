package ru.otus.spring.kreidun.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.otus.spring.kreidun.models.Comment;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RepositoryRestResource(path = "comment")
public interface CommentRepository  extends PagingAndSortingRepository<Comment, Long>
{
    List<Comment> findByBookId(Long id);
    List<Comment> findAll();
    void deleteAllByBookId(long bookId);
}