package ru.team.scheduler.oapi.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import ru.team.scheduler.oapi.services.EmailService;

import java.io.PrintWriter;
import java.io.StringWriter;

@Aspect
@Configuration
public class AppAOP {
    private final static Logger LOG = LoggerFactory.getLogger(AppAOP.class);
    @Value("{app.debug}")
    private String DEBUG;
    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Before("execution(* ru.team.scheduler.oapi.controllers.*.*(..))")
    private void controllersBefore(JoinPoint joinPoint) {
        LOG.info("Controller : " + joinPoint.toString());
    }

    @Before("execution(* ru.team.scheduler.oapi.services.*.*(..))")
    private void servicesBefore(JoinPoint joinPoint) {
        LOG.info("Service : " + joinPoint.toString());
    }

    @AfterThrowing(pointcut = "execution(* ru.team.scheduler.oapi..*.*(..))", throwing = "ex")
    private void throwingError(JoinPoint joinPoint, Exception ex) {
        String prefix = (Boolean.getBoolean(DEBUG)) ? "DEBUG" : "ERROR";
        LOG.error(prefix + " : " + joinPoint.toString());
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        emailService.sendSimpleMessage(new String[]{"spprtscheduler@gmail.com", "scheduler@internet.ru"}
                , prefix + " [Scheduler] " + ex
                , sw.toString());
    }
}
