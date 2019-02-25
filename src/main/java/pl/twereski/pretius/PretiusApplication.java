package pl.twereski.pretius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PretiusApplication {

    public static void main(String[] args) {
        SpringApplication.run(PretiusApplication.class, args);
    }

}
