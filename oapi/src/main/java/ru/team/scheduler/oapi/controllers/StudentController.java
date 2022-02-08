package ru.team.scheduler.oapi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team.scheduler.oapi.dto.UserDto;
import ru.team.scheduler.oapi.services.UserService;
import ru.team.sheduler.persist.entities.User;

import java.security.Principal;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String demo(){
        return "OK";
    }

    @GetMapping("/student_info")
    public UserDto personalAccount(Principal principal){
//        User user = userService.findByName(principal.getName()).orElseThrow(()-> new UsernameNotFoundException("Пользователь не найден в БД не найден!!!"));
        User user = userService.findByName("иван").orElseThrow(()-> new UsernameNotFoundException("Пользователь не найден в БД !!!"));
        return new UserDto(user);
    }
}
