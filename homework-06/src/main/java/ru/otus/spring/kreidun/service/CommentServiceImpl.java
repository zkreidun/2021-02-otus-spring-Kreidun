package ru.otus.spring.kreidun.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public boolean add(String text, Long bookId) {

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
    @Transactional
    public boolean del(Long commentId) {

        Comment comment = commentRepository.findById(commentId);
        if (comment == null) {
            ioService.printString("Комментарий не найден!");
            return false;
        }

        commentRepository.deleteById(commentId);
        return true;
    }

    @Override
    @Transactional
    public boolean upd(Long commentId, String text, Long bookId) {

        Book book = bookRepository.getById(bookId);
        if (book == null) {
            ioService.printString("Книга не найдена!");
            return false;
        }

        Comment comment = commentRepository.findById(commentId);
        if (comment == null) {
            ioService.printString("Комментарий не найден!");
            return false;
        }

        comment.setText(text);
        comment.setBook(book);
        commentRepository.save(comment);
        return true;
    }


    @Override
    @Transactional(readOnly = true)
    public void showAll() {

        String showComment;
        List<Comment> listComment = commentRepository.getAll();
        for (Comment comment : listComment) {
            showComment = " Comment: Id = " + comment.getId()+ " Comment = " + comment.getText() +
                        " Book: " + comment.getBook().getTitle() +
                        " Author: " + comment.getBook().getAuthor().getFirstName() +
                        " " + comment.getBook().getAuthor().getLastName();
            ioService.printString(showComment);
        }
    }
}
