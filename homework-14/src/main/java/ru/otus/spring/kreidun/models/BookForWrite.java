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
public class BookForWrite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false, unique = false)
    private String title;

    @Column(name = "author_id", nullable = false, unique = false)
    private Long author_id;

    @Column(name = "genre_id", nullable = false, unique = false)
    private Long genre_id;

    public BookForWrite(String title, Long author_id, Long genre_id)
    {
        this.title = title;
        this.author_id = author_id;
        this.genre_id = genre_id;
    }
}
