package ru.otus.spring.kreidun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.kreidun.service.TestingService;

@SpringBootApplication
public class StudentTestingApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(StudentTestingApplication.class, args);
		TestingService service = context.getBean(TestingService.class);
		service.runTesting();
	}
}

