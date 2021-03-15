package ru.otus.spring.kreidun.domain;

public class Question {

    private String strQuestion;
    private String strAnswer;

    public Question(String strQuestion, String strAnswer){
        this.strQuestion = strQuestion;
        this.strAnswer = strAnswer;
    }

    public String getStrQuestion() {
        return strQuestion;
    }

    public String getStrAnswer() {
        return strAnswer;
    }

    public void setStrQuestion(String strQuestion) {
        this.strQuestion = strQuestion;
    }

    public void setStrAnswer(String strAnswer) {
        this.strAnswer = strAnswer;
    }
}
