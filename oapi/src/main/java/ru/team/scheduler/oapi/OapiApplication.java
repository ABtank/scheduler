package ru.team.scheduler.oapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"oapi/src/main/java/ru/team/scheduler/oapi",
        "scheduler-database/src/main/java/ru/team/sheduler/persist"})
//@ComponentScan("ru/team")
public class OapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OapiApplication.class, args);

    }

}
