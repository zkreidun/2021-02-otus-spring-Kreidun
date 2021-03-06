package ru.otus.spring.kreidun.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @DisplayName("корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Question question = new Question("Вопрос", "Ответ");
        assertAll("question",
                () -> assertEquals("Вопрос", question.getStrQuestion()),
                () -> assertEquals("Ответ", question.getStrAnswer()));
    }

    @DisplayName("должен устанавливать вопрос")
    @Test
    void setStrQuestion() {
        String  strQuestionTest = "Тестый вопрос";
        Question question = new Question("Вопрос", "Ответ");
        question.setStrQuestion(strQuestionTest);
        assertEquals(strQuestionTest, question.getStrQuestion());
    }

    @DisplayName("должен устанавливать ответ")
    @Test
    void setStrAnswer() {
        String  strAnswerTest = "Тестый ответ";
        Question question = new Question("Вопрос", "Ответ");
        question.setStrAnswer(strAnswerTest);
        assertEquals(strAnswerTest, question.getStrAnswer());
    }
}