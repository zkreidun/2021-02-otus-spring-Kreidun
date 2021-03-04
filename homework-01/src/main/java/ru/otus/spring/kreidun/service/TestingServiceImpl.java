package ru.otus.spring.kreidun.service;

import ru.otus.spring.kreidun.dao.QuestionDao;
import ru.otus.spring.kreidun.domain.Question;
import ru.otus.spring.kreidun.domain.Testing;

public class TestingServiceImpl implements TestingService {

    private final QuestionDao questionDao;

    public TestingServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public void runTesting() {
        Testing testing = new Testing();
        testing.setQuestions(questionDao.getQuestions());

        for (Question question : testing.getQuestions()) {
            System.out.format("%s \n", question.getStrQuestion());
        }
    }
}
