package ru.otus.spring.kreidun.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.kreidun.models.Comment;
import ru.otus.spring.kreidun.services.CommentService;

import java.util.List;

@Controller
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/commentsbook")
    public String getListBook(@RequestParam("id") long id, Model model) {

        List<Comment> comments = commentService.findCommentByBookId(id);
        model.addAttribute("comments", comments);
        model.addAttribute("bookid", id);
        return "listComments";
    }
}
