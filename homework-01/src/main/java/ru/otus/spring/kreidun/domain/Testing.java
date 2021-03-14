package ru.otus.spring.kreidun.domain;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Testing {

    private Student student;
    private List<Question> questions;
    private int trueAnswersCount;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getTrueAnswersCount() {
        return trueAnswersCount;
    }

    public void IncrementTrueAnswersCount() {
        this.trueAnswersCount++;
    }
}
