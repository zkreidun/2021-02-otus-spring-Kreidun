package ru.otus.spring.kreidun.dao;

import ru.otus.spring.kreidun.domain.Question;

import java.util.List;

public interface QuestionDao {
    public void loadQuestions();
    public List<Question> getQuestions();
}
