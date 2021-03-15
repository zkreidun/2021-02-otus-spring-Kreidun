package ru.otus.spring.kreidun.dao;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/*
    Класс ограничивающий контекст. В тесты, лежащие в одном пакете с данной конфигурацией попадут только те бины, которые лежать в пакетах, указанных в ComponentScan
    Подробнее см. https://otus.ru/nest/post/429/
*/
@SpringBootConfiguration
@EnableConfigurationProperties
@ComponentScan({"ru.otus.spring.kreidun.configuration", "ru.otus.spring.kreidun.dao", "ru.otus.spring.kreidun.service", "ru.otus.spring.kreidun.domain"})
public class TestContextConfig {
}
