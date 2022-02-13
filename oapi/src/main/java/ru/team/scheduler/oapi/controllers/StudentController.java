package ru.team.scheduler.oapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team.scheduler.oapi.dto.LessonDto;
import ru.team.scheduler.oapi.dto.UserDto;
import ru.team.scheduler.oapi.services.UserServiceImpl;
import ru.team.scheduler.persist.entities.User;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private UserServiceImpl userServiceImpl;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userServiceImpl = userService;
    }

    @GetMapping()
    public String demo(){
        return "OK";
    }

    @GetMapping("/student_info")
    public UserDto personalAccount(Principal principal){
        User user = userServiceImpl.findByName("иван").orElseThrow(()-> new UsernameNotFoundException("Пользователь не найден в БД !!!"));
        return new UserDto(user);
    }

    @GetMapping("/lessons")
    public List<LessonDto> getLessons(){

        return null;
    }



}
