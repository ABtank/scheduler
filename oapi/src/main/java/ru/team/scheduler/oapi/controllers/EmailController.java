package ru.team.scheduler.oapi.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.constants.SwaggerConstant;
import ru.team.scheduler.oapi.dto.EmailDto;
import ru.team.scheduler.oapi.services.EmailService;
import springfox.documentation.annotations.ApiIgnore;

import javax.mail.MessagingException;
import java.security.Principal;

@Slf4j
@RequestMapping("/api/v1/support")
@Api(tags = {SwaggerConstant.API_SUPPORT})
@RestController
@AllArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @ApiOperation(value = "Отправить письмо в тех поддержку.", notes = "Если вы нашли ошибку или у вас есть идеи для улучшения проекта, то отправьте нам письмо с предложением.", response = EmailDto.class)
    @PostMapping("/email")
    @ResponseStatus(HttpStatus.CREATED)
    public EmailDto sendEmailToSupport(
            @ApiParam(name = "Письмо", value = "Объект Письмо в формате Json", required = true)
            @Validated
            @RequestBody EmailDto mailDto,
            @ApiIgnore Principal principal) {
        try {
            emailService.sendEmailToSupport(mailDto, principal);
        } catch (MessagingException ignored) {

        }
        return mailDto;
    }
}
