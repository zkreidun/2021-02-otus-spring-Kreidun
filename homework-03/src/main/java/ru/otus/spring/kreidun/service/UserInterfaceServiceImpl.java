package ru.otus.spring.kreidun.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.kreidun.domain.Question;
import ru.otus.spring.kreidun.domain.Student;

import java.util.Scanner;

@Service
public class UserInterfaceServiceImpl implements UserInterfaceService{

    private final Scanner scanner;

    {
        scanner = new Scanner(System.in);
    }

    @Override
    public Student RegisteringStudent(String questionFirstName, String questionLastName) {

        System.out.println(questionFirstName);
        String firstName = scanner.nextLine();
        System.out.println(questionLastName);
        String lastName = scanner.nextLine();
        Student student = new Student(firstName, lastName);
        return student;
    }

    @Override
    public String askQuestion(Question question, int numberQuestion) {

        String answer;
        System.out.print(question.getStrQuestion());
        answer = scanner.nextLine();
        return answer;
    }

    @Override
    public void showResult(String resultStr) {
        System.out.println(resultStr);
    }
}
