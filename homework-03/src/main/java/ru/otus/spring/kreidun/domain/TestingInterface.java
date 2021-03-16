package ru.otus.spring.kreidun.domain;

import java.util.List;

public interface TestingInterface {

    public Student getStudent();

    public void setStudent(Student student);

    public List<Question> getQuestions();

    public void setQuestions(List<Question> questions);

    public int getTrueAnswersCount();

    public void IncrementTrueAnswersCount();
}
