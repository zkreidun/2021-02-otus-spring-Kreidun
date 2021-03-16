package ru.otus.spring.kreidun.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("answers")
public class ConfigAnswers {

    private int minimumTrueAnswers;

    public void setMinimumTrueAnswers(int countToOk) {

        this.minimumTrueAnswers = countToOk;
    }

    public int getMinimumTrueAnswers() {

        return this.minimumTrueAnswers;
    }
}