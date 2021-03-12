package ru.otus.spring.kreidun.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.kreidun.configuration.Config;
import ru.otus.spring.kreidun.dao.QuestionDao;
import ru.otus.spring.kreidun.domain.Question;
import ru.otus.spring.kreidun.domain.Student;
import ru.otus.spring.kreidun.domain.Testing;

@Service
public class TestingServiceImpl implements TestingService {

    private final QuestionDao questionDao;
    private final UserInterfaceService userInterfaceService;
    private final Config config;
    private final Testing testing;

    public TestingServiceImpl(QuestionDao questionDao,
                           UserInterfaceService userInterfaceService,
                           Config config,
                           Testing testing) {

        this.questionDao = questionDao;
        this.userInterfaceService = userInterfaceService;
        this.config = config;
        this.testing = testing;
    }

    private void initStudent() {

        Student student = new Student();
        student = userInterfaceService.RegisteringStudent("Enter your firstname:","Enter your lastname:");
        testing.setStudent(student);
    }

    public void setQuestions() {

        questionDao.loadQuestions(config.getQuestionsFileName());
        testing.setQuestions(questionDao.getQuestions());
    }

    @Override
    public void initTesting() {

        initStudent();
        setQuestions();
    }

    @Override
    public void runTesting() {

        int questionNumber = 0;
        String answer;

        for (Question question : testing.getQuestions()) {
            questionNumber++;
            answer = userInterfaceService.askQuestion(question, questionNumber);
            if (answer.equals(question.getStrAnswer())) {
                testing.IncrementTrueAnswersCount();
            }
        }
    }

    @Override
    public void showResultTesting() {

        String resultStr = "Student: " + testing.getStudent().getFirstname() + " " + testing.getStudent().getLastname() + "\n";
        if (testing.getTrueAnswersCount() >= config.getMinimumTrueAnswers()) {
            resultStr += "Testing result: OK";
        }
        else{
            resultStr += "Testing result: Fail";
        }
        userInterfaceService.showResult(resultStr);
    }
}
