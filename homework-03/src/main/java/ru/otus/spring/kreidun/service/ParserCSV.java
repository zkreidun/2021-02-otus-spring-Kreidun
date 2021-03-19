package ru.otus.spring.kreidun.service;

import ru.otus.spring.kreidun.domain.Question;

import java.util.List;
import java.util.Scanner;

public interface ParserCSV {
    public List<Question> parsingFile(Scanner scanner);
}
