package ru.otus.spring.kreidun.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.kreidun.configuration.ConfigAnswers;
import ru.otus.spring.kreidun.dao.QuestionDao;
import ru.otus.spring.kreidun.domain.Question;
import ru.otus.spring.kreidun.domain.Student;
import ru.otus.spring.kreidun.domain.TestingInterface;

@Service
public class TestingServiceImpl implements TestingService {

    private final QuestionDao questionDao;
    private final UserInterfaceService userInterfaceService;
    private final TestingInterface testing;
    private final MessageService messageService;
    private final ConfigAnswers configAnswers;

    public TestingServiceImpl(QuestionDao questionDao,
                              UserInterfaceService userInterfaceService,
                              TestingInterface testing,
                              MessageService messageService,
                              ConfigAnswers configAnswers) {

        this.questionDao = questionDao;
        this.userInterfaceService = userInterfaceService;
        this.configAnswers = configAnswers;
        this.testing = testing;
        this.messageService = messageService;
    }

    private void initStudent() {

        Student student = new Student();
        student = userInterfaceService.RegisteringStudent(messageService.getMessage("question.firstname"),
                messageService.getMessage("question.lastname"));
        testing.setStudent(student);
    }

    public void setQuestions() {

        questionDao.loadQuestions();
        testing.setQuestions(questionDao.getQuestions());
    }

    public void initTesting() {

        initStudent();
        setQuestions();
    }

    @Override
    public void runTesting() {

        initTesting();

        int questionNumber = 0;
        String answer;

        for (Question question : testing.getQuestions()) {
            questionNumber++;
            answer = userInterfaceService.askQuestion(question, questionNumber);
            if (answer.equals(question.getStrAnswer())) {
                testing.IncrementTrueAnswersCount();
            }
        }

        showResultTesting();
    }

    public void showResultTesting() {

        String resultStr = "Student: " + testing.getStudent().getFirstname() + " " + testing.getStudent().getLastname() + "\n";
        if (testing.getTrueAnswersCount() >= configAnswers.getMinimumTrueAnswers()) {
            resultStr = messageService.getMessage("result.ok");
        }
        else{
            resultStr = messageService.getMessage("result.failed");
        }
        Object[] formatResult = new Object[4];
        formatResult[0] = testing.getStudent().getLastname() + " " + testing.getStudent().getFirstname();
        formatResult[1] = resultStr;
        formatResult[2] = testing.getTrueAnswersCount();

        userInterfaceService.showResult(messageService.getMessageFormat("result.show", formatResult));
    }

}
