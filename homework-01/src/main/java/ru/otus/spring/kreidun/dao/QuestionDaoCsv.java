package ru.otus.spring.kreidun.dao;

import org.springframework.stereotype.Repository;
import ru.otus.spring.kreidun.domain.Question;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Repository
public class QuestionDaoCsv implements QuestionDao{

    private List<Question> questions;

    {
        questions = new ArrayList<>();
    }

    @Override
    public void loadQuestions(String questionsFileName) {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(questionsFileName);
        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(",");
            questions.add(new Question(line[0], line[1]));
        }
    }

    @Override
    public List<Question> getQuestions() {

        return questions;
    }
}
