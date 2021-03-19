package ru.otus.spring.kreidun.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест Question")
@SpringBootTest
class QuestionTest {

    @DisplayName("корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Question question = new Question("Вопрос", "Ответ");
        assertAll("question",
                () -> assertEquals("Вопрос", question.getStrQuestion()),
                () -> assertEquals("Ответ", question.getStrAnswer()));
    }

    @DisplayName("должен корректно устанавливать вопрос")
    @Test
    void shouldSetCorrectQuestion() {
        String  strQuestionTest = "Тестый вопрос";
        Question question = new Question("Вопрос", "Ответ");
        question.setStrQuestion(strQuestionTest);
        assertEquals(strQuestionTest, question.getStrQuestion());
    }

    @DisplayName("должен корректно устанавливать ответ")
    @Test
    void shouldSetCorrectAnswer() {
        String  strAnswerTest = "Тестый ответ";
        Question question = new Question("Вопрос", "Ответ");
        question.setStrAnswer(strAnswerTest);
        assertEquals(strAnswerTest, question.getStrAnswer());
    }
}