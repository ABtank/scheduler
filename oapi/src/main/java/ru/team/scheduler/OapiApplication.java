package ru.team.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
//import ru.team.scheduler.oapi.services.EmailService;

import javax.mail.MessagingException;

@SpringBootApplication
@PropertySource("classpath:secured.properties")
public class OapiApplication {
    //    Пример рассылки
//    private EmailService emailService;
//    @Autowired
//    public void setEmailService(EmailService emailService) throws MessagingException {
//        this.emailService = emailService;
//        emailService.sendSimpleMessage(new String[]{"spprtscheduler@gmail.com","scheduler@internet.ru"},"Заголовок","Какой-то там текст.");
//        emailService.sendMessageWithAttachment(new String[]{"spprtscheduler@gmail.com","scheduler@internet.ru"},"Заголовок с файлом","Какой-то там текст.","oapi/src/main/resources/application.yaml");
//    }
    public static void main(String[] args) {
        SpringApplication.run(OapiApplication.class, args);
    }

}
