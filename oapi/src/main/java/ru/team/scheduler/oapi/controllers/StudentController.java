package ru.team.scheduler.oapi.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.team.scheduler.oapi.constants.SwaggerConstant;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.persist.dto.LessonByIdDto;
import ru.team.scheduler.persist.dto.StudentScheduleDto;
import ru.team.scheduler.oapi.dto.UserDto;
import ru.team.scheduler.oapi.services.LessonsStudentsServiceImpl;
import ru.team.scheduler.oapi.services.StudentService;
import ru.team.scheduler.oapi.services.UserServiceImpl;
import ru.team.scheduler.persist.entities.User;

import java.security.Principal;
import java.util.List;

@RestController
@Api(tags = {SwaggerConstant.API_USER})
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

    @ApiOperation(value = "Ping",notes = "Тест")
    @GetMapping()
    public String demo(){
        return "OK";
    }


    @ApiOperation(value = "Карточка Залогиненого.",notes = "В данном случае Студента.")
    @ApiModelProperty(hidden = true,accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    @GetMapping("/student_info")
    public UserDto personalAccount(Principal principal){
        return userServiceImpl.findByEmail(principal.getName()).orElseThrow(()-> new UsernameNotFoundException("Пользователь не найден в БД !!!"));
    }

    //возвращает все лекции по текущему user(студенту)
    @GetMapping("/lessons")
    @Transactional
    public List<StudentScheduleDto> getLessons(Principal principal){
        UserDto userDto = userServiceImpl.findByEmail(principal.getName()).orElseThrow(()-> new UsernameNotFoundException("Пользователь не найден в БД !!!"));
        return studentService.getScheduleByUser(userDto.getId());
    }

    //возвращает расписание учителя по ссылке
    @GetMapping("/lessons/{id}")
    public LessonByIdDto getLessons(@PathVariable Integer id){
        return studentService.getLessonById(id).orElseThrow(() -> new NotFoundException());
    }

    //удалить запись на урок (освободить место)
    @DeleteMapping("/lessons/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Integer id) {
        studentService.deleteLessonById(id);
    }

    //записаться на лекцию
    @PutMapping("/lessons/{id}")
    public List<StudentScheduleDto> subscribe(@PathVariable(name = "id") Integer lesson_id, Principal principal){
        UserDto userDto = userServiceImpl.findByEmail(principal.getName()).orElseThrow(()-> new UsernameNotFoundException("Пользователь не найден в БД !!!"));
        return studentService.reserveLecture(lesson_id, userDto);
    }


}
