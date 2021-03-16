package ru.otus.spring.kreidun.service;

import ru.otus.spring.kreidun.domain.Question;
import ru.otus.spring.kreidun.domain.Student;

public interface UserInterfaceService {

    Student RegisteringStudent(String questionFirstName, String questionLastName);

    String askQuestion(Question question, int numberQuestion);

    void showResult(String resultStr);
}
