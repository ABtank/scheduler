package ru.team.scheduler.oapi.services;

import ru.team.scheduler.oapi.dto.EmailDto;

import javax.mail.MessagingException;
import java.security.Principal;

public interface EmailService {
    void sendSimpleMessage(String[] to, String subject, String text);
    void sendMessageWithAttachment(
            String[] to, String subject, String text, String pathToAttachment) throws MessagingException;

    void sendEmailToSupport(EmailDto mailDto, Principal principal) throws MessagingException;
}
