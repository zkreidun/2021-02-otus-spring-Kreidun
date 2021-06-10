package ru.otus.spring.kreidun.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class AuthorForWrite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstname", nullable = false, unique = false)
    private String firstName;

    @Column(name = "lastname", nullable = false, unique = false)
    private String lastName;

    public AuthorForWrite(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }
}