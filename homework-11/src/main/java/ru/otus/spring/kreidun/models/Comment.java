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
@Document(collection =  "comments")
public class Comment {

    @Id
    private String id;

    @JsonProperty("text")
    @Field(name = "text")
    private String text;

    @DBRef
    private Book book;

    public Comment (String text, Book book)
    {
        this.text = text;
        this.book = book;
    }
}
