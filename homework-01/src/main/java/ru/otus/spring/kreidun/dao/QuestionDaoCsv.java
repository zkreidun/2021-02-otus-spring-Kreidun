package ru.otus.spring.kreidun.dao;

import ru.otus.spring.kreidun.domain.Question;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionDaoCsv implements QuestionDao{

    private String questionsFileName;

    public String getQuestionsFileName() {
        return questionsFileName;
    }

    public void setQuestionsFileName(String questionsFileName) {
        this.questionsFileName = questionsFileName;
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(questionsFileName);
        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split(",");
            questions.add(new Question(line[0], line[1]));
        }

        return questions;
    }
}
