package ru.team.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = "ru.team.scheduler")
public class OapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OapiApplication.class, args);
    }

}
