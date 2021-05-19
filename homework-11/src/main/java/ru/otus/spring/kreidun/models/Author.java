package ru.otus.spring.kreidun.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "authors")
public class Author {

    @Id
    private String id;

    @JsonProperty("firstname")
    @Field(name = "firstname")
    private String firstName;

    @JsonProperty("lastname")
    @Field(name = "lastname")
    private String lastName;

    @DBRef
    private List<Book> books;

    public Author (String firstname, String lastname)
    {
        this.firstName = firstname;
        this.lastName = lastname;
    }
}
