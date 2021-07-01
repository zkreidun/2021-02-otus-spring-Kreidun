package ru.otus.spring.kreidun.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MyHealthIndicator implements HealthIndicator {

    private final Random random = new Random();

    @Override
    public Health health() {
        int code = random.nextInt();
        if ((code % 2) > 0){
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("Error code ",  code)
                    .build();
        } else {
            return Health.up().withDetail("Message ", "success!").build();
        }
    }
}
