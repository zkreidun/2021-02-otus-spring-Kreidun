package ru.otus.spring.kreidun.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.spring.kreidun.service.TestingService;

@ShellComponent
public class StudentTestingShell {

    private final TestingService service;
    private boolean isRegistrationPassed = false;

    public StudentTestingShell(TestingService service) {
        this.service = service;
    }

    @ShellMethod(key = {"register", "rg"}, value = "Registration student for testing")
    public void regStudent(){
        service.regStudent();
        isRegistrationPassed = true;
    }

    @ShellMethod(key = {"run", "r"}, value = "Run student testing")
    @ShellMethodAvailability(value = "isRunStudentTestingCommandAvailable")
    public void runStudentTesting(){
        service.runTesting();
    }

    private Availability isRunStudentTestingCommandAvailable() {
        return isRegistrationPassed == false ? Availability.unavailable("Сначала зарегистрируйтесь!") : Availability.available();
    }
}
