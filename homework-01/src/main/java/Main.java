import org.springframework.context.annotation.*;
import ru.otus.spring.kreidun.domain.Testing;
import ru.otus.spring.kreidun.service.TestingService;

@Configuration
@ComponentScan("ru.otus")
@PropertySource("classpath:application.properties")
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        TestingService service = context.getBean(TestingService.class);
        service.initTesting();
        service.runTesting();
        service.showResultTesting();
    }
}
