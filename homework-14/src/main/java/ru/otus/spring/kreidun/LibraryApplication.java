package ru.otus.spring.kreidun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(LibraryApplication.class, args);
	}
}
