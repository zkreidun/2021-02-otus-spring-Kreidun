package ru.otus.spring.kreidun.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class CommentForWrite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "text", nullable = false, unique = false)
    private String text;

    @Column(name = "book_id", nullable = false, unique = false)
    private Long book_id;

    public CommentForWrite (String text, Long book_id)
    {
        this.text = text;
        this.book_id = book_id;
    }
}
