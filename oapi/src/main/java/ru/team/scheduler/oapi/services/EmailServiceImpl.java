package ru.team.scheduler.oapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import ru.team.scheduler.oapi.controllers.mappers.UserMapper;
import ru.team.scheduler.oapi.dto.EmailDto;
import ru.team.scheduler.oapi.dto.UserDto;
import ru.team.scheduler.oapi.exceptions.NotFoundException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.security.Principal;
import java.util.Objects;

@Component
public class EmailServiceImpl implements EmailService {

    @Value("${mail.name}")
    private String from;

    private JavaMailSender emailSender;
    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setEmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendSimpleMessage(
            String[] to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void sendMessageWithAttachment(
            String[] to, String subject, String text, String pathToAttachment) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        FileSystemResource file
                = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);

        emailSender.send(message);
    }

    @Override
    public void sendEmailToSupport(EmailDto mailDto, Principal principal) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        String[] cc = new String[mailDto.getCc().size()];
        mailDto.getCc().toArray(cc);
        helper.setCc(cc);
        mailDto.getTo().add(from);
        helper.setTo(mailDto.getTo().toArray(new String[0]));
        helper.setSubject(mailDto.getHeader());
        UserDto userDto = userService
                .findByEmail(principal.getName())
                .map(userMapper::userToDto)
                .orElseThrow(NotFoundException::new);
        helper.setText(mailDto.getBody() + "\n\n\n От:\n" + userDto.getInfo());
        emailSender.send(message);
    }

}