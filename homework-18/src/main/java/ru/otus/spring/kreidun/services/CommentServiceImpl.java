package ru.otus.spring.kreidun.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.kreidun.models.Comment;
import ru.otus.spring.kreidun.repositories.CommentRepository;

import java.util.Collections;
import java.util.List;

import static ru.otus.spring.kreidun.utils.Util.getRandomSleep;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    @HystrixCommand(groupKey = "CommentGroup", commandKey = "getAllCommentsCommand",
            fallbackMethod = "getReserveListComments")
    public List<Comment> findCommentByBookId(Long id) {
        getRandomSleep();
        return commentRepository.findByBookId(id);
    }

    public List<Comment> getReserveListComments(Long id) {
        return Collections.emptyList();
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteAllCommentsByBookId(long bookId) {
        commentRepository.deleteAllByBookId(bookId);
    }
}
