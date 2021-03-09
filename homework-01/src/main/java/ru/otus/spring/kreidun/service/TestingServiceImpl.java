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

    public TestingServiceImpl(QuestionDao questionDao,
                           UserInterfaceService userInterfaceService,
                           Config config) {

        this.questionDao = questionDao;
        this.userInterfaceService = userInterfaceService;
        this.config = config;
    }

    private void initStudent(Testing testing) {

        Student student = new Student();
        student = userInterfaceService.RegisteringStudent("Enter your firstname:","Enter your lastname:");
        testing.setStudent(student);
    }

    public void setQuestions(Testing testing) {

        questionDao.loadQuestions(config.getQuestionsFileName());
        testing.setQuestions(questionDao.getQuestions());
    }

    @Override
    public Testing initTesting() {

        Testing testing = new Testing();
        initStudent(testing);
        setQuestions(testing);
        return testing;
    }

    @Override
    public void runTesting(Testing testing) {

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
    public void showResultTesting(Testing testing) {

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
