package ru.otus.spring.kreidun.domain;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Testing implements TestingInterface{

    private Student student;
    private List<Question> questions;
    private int trueAnswersCount;

    @Override
    public Student getStudent() {
        return student;
    }

    @Override
    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public int getTrueAnswersCount() {
        return trueAnswersCount;
    }

    @Override
    public void IncrementTrueAnswersCount() {
        this.trueAnswersCount++;
    }
}
