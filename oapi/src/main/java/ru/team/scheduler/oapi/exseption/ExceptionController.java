package ru.team.scheduler.oapi.exseption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.team.scheduler.oapi.dto.ProjectError;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler
    public ResponseEntity<?> handleInvalidParamsException(UsernameNotFoundException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ProjectError(HttpStatus.UNAUTHORIZED.value(), e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleInvalidParamsException(UserNotFoundException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ProjectError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
