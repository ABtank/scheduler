package ru.team.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ru/team")
//@ComponentScan({"oapi.src/main/java/ru/team/scheduler/oapi",
//                    "scheduler-database.src/main/java/ru/team/scheduler/persist"})
public class OapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OapiApplication.class, args);

    }

}
