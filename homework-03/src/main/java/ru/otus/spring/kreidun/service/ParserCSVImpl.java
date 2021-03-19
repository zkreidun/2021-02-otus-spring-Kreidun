package ru.otus.spring.kreidun.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.kreidun.domain.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class ParserCSVImpl implements ParserCSV{

    @Override
    public List<Question> parsingFile(Scanner scanner) {

        List<Question> questions = new ArrayList<>();

        while (scanner.hasNext()) {
            String[] strings = scanner.nextLine().split(",");
            questions.add(new Question(strings[0], strings[1]));
        }

        return questions;
    }
}
