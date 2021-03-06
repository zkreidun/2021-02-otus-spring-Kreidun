package ru.otus.spring.kreidun.domain;

import java.util.List;

public class Testing {

    private Student student;
    private List<Question> questions;

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
}
