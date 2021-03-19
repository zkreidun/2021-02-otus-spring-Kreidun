package ru.otus.spring.kreidun.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.kreidun.domain.Question;
import ru.otus.spring.kreidun.configuration.ConfigLocale;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Тест QuestionDao")
@SpringBootTest
class QuestionDaoCsvTest {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private ConfigLocale configLocale;

    @DisplayName("должен корректно загружать вопросы")
    @Test
    void shouldCorrectLoadQuestions() {

        questionDao.loadQuestions();

        if (configLocale.getLocale().toString().equals("ru_RU"))
        {
            assertThat(questionDao.getQuestions()).extracting(Question::getStrQuestion, Question::getStrAnswer)
                    .containsExactly(tuple("Вопрос: 1*1=", "1"),
                            tuple("Вопрос: 2*2=", "4"),
                            tuple("Вопрос: 3*3=", "9"),
                            tuple("Вопрос: 4*4=", "16"),
                            tuple("Вопрос: 5*5=", "25"));
        }

        if (configLocale.getLocale().toString().equals("en_US"))
        {
            assertThat(questionDao.getQuestions()).extracting(Question::getStrQuestion, Question::getStrAnswer)
                    .containsExactly(tuple("Question: 1*1=", "1"),
                            tuple("Question: 2*2=", "4"),
                            tuple("Question: 3*3=", "9"),
                            tuple("Question: 4*4=", "16"),
                            tuple("Question: 5*5=", "25"));
        }


    }
}