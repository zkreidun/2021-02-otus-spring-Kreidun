package ru.otus.spring.kreidun.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection =  "books")
public class Book {

    @Id
    private String id;

    @JsonProperty("title")
    @Field(name = "title")
    private String title;

    @DBRef
    private Author author;

    @DBRef
    private Genre genre;

    public Book (String title, Author author, Genre genre )
    {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
