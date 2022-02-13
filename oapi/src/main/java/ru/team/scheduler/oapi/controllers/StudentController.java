package ru.team.scheduler.oapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team.scheduler.oapi.dto.LessonsStudentsDto;
import ru.team.scheduler.oapi.dto.StudentScheduleDto;
import ru.team.scheduler.oapi.dto.UserDto;
import ru.team.scheduler.oapi.services.LessonsStudentsServiceImpl;
import ru.team.scheduler.oapi.services.StudentService;
import ru.team.scheduler.oapi.services.UserServiceImpl;
import ru.team.scheduler.persist.entities.User;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private UserServiceImpl userServiceImpl;
    private LessonsStudentsServiceImpl lessonsStudentsService;
    private StudentService studentService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userServiceImpl = userService;
    }
    @Autowired
    public void setLessonsStudentsService(LessonsStudentsServiceImpl lessonsStudentsService) {
        this.lessonsStudentsService = lessonsStudentsService;
    }
    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public String demo(){
        return "OK";
    }

    //возвращает личные данные текущего user
    @GetMapping("/student_info")
    public UserDto personalAccount(Principal principal){
        User user = userServiceImpl.findByName(principal.getName()).orElseThrow(()-> new UsernameNotFoundException("Пользователь не найден в БД !!!"));
        return new UserDto(user);
    }

    //возвращает все лекции по текущему user(студенту)
    @GetMapping("/lessons")
    @Transactional
    public List<StudentScheduleDto> getLessons(Principal principal){
        User user = userServiceImpl.findByName(principal.getName()).orElseThrow(()-> new UsernameNotFoundException("Пользователь не найден в БД !!!"));
        return studentService.getScheduleByUser(user.getId());
    }




}
