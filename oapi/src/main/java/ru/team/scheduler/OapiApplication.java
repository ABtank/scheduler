package ru.team.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.team.scheduler.oapi.services.EmailService;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
public class OapiApplication {
    //    Пример рассылки
    private EmailService emailService;

    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));   // It will set UTC timezone
        System.out.println("Spring boot application running in UTC timezone :"+new Date());   // It will print UTC timezone
    }

    @Autowired
    public void setEmailService(EmailService emailService) throws MessagingException {
        this.emailService = emailService;
//        emailService.sendSimpleMessage(new String[]{"spptr@mail.ru"},"Заголовок","Какой-то там текст.");
//        emailService.sendMessageWithAttachment(new String[]{"spprtscheduler@gmail.com","scheduler@internet.ru"},"Заголовок с файлом","Какой-то там текст.","oapi/src/main/resources/application.yaml");
    }

    public static void main(String[] args) {
        SpringApplication.run(OapiApplication.class, args);
    }

}
