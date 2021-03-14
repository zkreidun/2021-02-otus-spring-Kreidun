package ru.otus.spring.kreidun.dao;

import org.junit.jupiter.api.Test;
import ru.otus.spring.kreidun.domain.Question;

import static org.assertj.core.api.Assertions.*;


class QuestionDaoCsvTest {

    @Test
    void loadQuestions() {
        QuestionDaoCsv questionDao = new QuestionDaoCsv();
        questionDao.loadQuestions("test-questions.csv");

        assertThat(questionDao.getQuestions()).extracting(Question::getStrQuestion, Question::getStrAnswer)
                .containsExactly(tuple("1*1=", "1"),
                        tuple("2*2=", "4"),
                        tuple("3*3=", "9"),
                        tuple("4*4=", "16"),
                        tuple("5*5=", "25"));
    }

    @Test
    void getQuestions() {
        QuestionDaoCsv questionDao = new QuestionDaoCsv();
        questionDao.loadQuestions("test-questions.csv");

        assertThat(questionDao.getQuestions()).extracting(Question::getStrQuestion, Question::getStrAnswer)
                .containsExactly(tuple("1*1=", "1"),
                        tuple("2*2=", "4"),
                        tuple("3*3=", "9"),
                        tuple("4*4=", "16"),
                        tuple("5*5=", "25"));
    }
}