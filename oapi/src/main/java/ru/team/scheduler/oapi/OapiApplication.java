package ru.team.scheduler.oapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan("scheduler-database/src/main/java/ru/team/sheduler/persist, " +
//        "oapi/src/main/java/ru/team/scheduler/oapi")
@ComponentScan({"src/main/java/ru/team/scheduler/oapi",
                    "src/main/java/ru/team/sheduler/persist"})
public class OapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OapiApplication.class, args);

    }

}
