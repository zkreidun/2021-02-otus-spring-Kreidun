package ru.otus.spring.kreidun.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    public boolean add(String text, String bookId) {

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty()) {
            ioService.printString("Книга не найдена!");
            return false;
        }
        Book book = bookOptional.get();

        Comment comment = new Comment(text, book);
        commentRepository.save(comment);
        return true;
    }

    @Override
    public boolean del(String commentId) {

        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isEmpty()) {
            ioService.printString("Комментарий не найден!");
            return false;
        }

        commentRepository.deleteById(commentId);
        return true;
    }

    @Override
    public boolean upd(String commentId, String text, String bookId) {

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty()) {
            ioService.printString("Книга не найдена!");
            return false;
        }
        Book book = bookOptional.get();

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
