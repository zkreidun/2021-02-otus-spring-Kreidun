package ru.otus.spring.kreidun.service;

import ru.otus.spring.kreidun.domain.Testing;

public interface TestingService {
    public Testing initTesting();
    public void runTesting(Testing testing);
    public void showResultTesting(Testing testing);
}
