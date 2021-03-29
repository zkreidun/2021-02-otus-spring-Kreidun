package ru.otus.spring.kreidun.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false, unique = false)
    private String title;

    @ManyToOne (targetEntity = Author.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(targetEntity = Genre.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;
}
