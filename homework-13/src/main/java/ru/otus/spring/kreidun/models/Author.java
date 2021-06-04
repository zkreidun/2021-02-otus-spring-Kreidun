package ru.otus.spring.kreidun.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstname", nullable = false, unique = false)
    private String firstName;

    @Column(name = "lastname", nullable = false, unique = false)
    private String lastName;
}
