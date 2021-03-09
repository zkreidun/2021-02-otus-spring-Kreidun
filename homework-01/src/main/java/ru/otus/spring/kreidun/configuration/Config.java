package ru.otus.spring.kreidun.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Config {

    @Value("${questionsFileName}")
    private String questionsFileName;

    @Value("${minimumTrueAnswers}")
    private int minimumTrueAnswers;

    public String getQuestionsFileName() {
        return questionsFileName;
    }

    public int getMinimumTrueAnswers() {
        return minimumTrueAnswers;
    }

}
