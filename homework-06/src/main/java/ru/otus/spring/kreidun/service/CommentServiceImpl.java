package ru.otus.spring.kreidun.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.kreidun.models.Book;
import ru.otus.spring.kreidun.models.Comment;
import ru.otus.spring.kreidun.repositories.BookRepository;
import ru.otus.spring.kreidun.repositories.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final IOService ioService;

    @Override
    public boolean addNewComment(String text, Long bookId) {

        Book book = bookRepository.getById(bookId);
        if (book == null) {
            ioService.printString("Книга не найдена!");
            return false;
        }

        Comment comment = new Comment(0, text, book);
        commentRepository.save(comment);
        return true;
    }

    @Override
    public boolean deleteComment(Long commentId) {

        Comment comment = commentRepository.findById(commentId);
        if (comment == null) {
            ioService.printString("Комментарий не найден!");
            return false;
        }

        commentRepository.deleteById(commentId);
        return true;
    }

    @Override
    public boolean updateComment(Long commentId, String text, Long bookId) {

        Book book = bookRepository.getById(bookId);
        if (book == null) {
            ioService.printString("Книга не найдена!");
            return false;
        }

        commentRepository.update(commentId, text, bookId);
        return true;
    }


    @Override
    public void showAllComments() {

        String showComment;
        List<Comment> listComment = commentRepository.getAll();
        for (Comment comment : listComment) {
            showComment = " Comment: Id = " + comment.getId()+ " Comment = " + comment.getText() +
                        " Book: " + comment.getBook().getTitle();
            ioService.printString(showComment);
        }
    }
}
