package ru.otus.spring.kreidun.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.kreidun.models.Book;
import ru.otus.spring.kreidun.models.Comment;
import ru.otus.spring.kreidun.repositories.BookRepository;
import ru.otus.spring.kreidun.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final IOService ioService;

    @Override
    @Transactional
    public boolean add(String text, long bookId) {

        Book book = bookRepository.findById(bookId);
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
    public boolean del(long commentId) {

        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isEmpty()) {
            ioService.printString("Комментарий не найден!");
            return false;
        }

        commentRepository.deleteById(commentId);
        return true;
    }

    @Override
    @Transactional
    public boolean upd(long commentId, String text, long bookId) {

        Book book = bookRepository.findById(bookId);
        if (book == null) {
            ioService.printString("Книга не найдена!");
            return false;
        }

        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isEmpty()) {
            ioService.printString("Комментарий не найден!");
            return false;
        }
        Comment comment = commentOptional.get();

        comment.setText(text);
        comment.setBook(book);
        commentRepository.save(comment);
        return true;
    }


    @Override
    @Transactional(readOnly = true)
    public void showAll() {

        String showComment;
        List<Comment> listComment = commentRepository.findAll();
        for (Comment comment : listComment) {
            showComment = " Comment: Id = " + comment.getId()+ " Comment = " + comment.getText() +
                        " Book: " + comment.getBook().getTitle() +
                        " Author: " + comment.getBook().getAuthor().getFirstName() +
                        " " + comment.getBook().getAuthor().getLastName();
            ioService.printString(showComment);
        }
    }
}
