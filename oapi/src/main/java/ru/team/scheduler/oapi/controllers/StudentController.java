package ru.team.scheduler.oapi.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.constants.SwaggerConstant;
import ru.team.scheduler.oapi.controllers.mappers.UserMapper;
import ru.team.scheduler.oapi.dto.LessonsStudentsDto;
import ru.team.scheduler.oapi.dto.UserDto;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.oapi.services.LessonsStudentsServiceImpl;
import ru.team.scheduler.oapi.services.StudentService;
import ru.team.scheduler.oapi.services.UserService;
import ru.team.scheduler.persist.dto.LessonByIdDto;
import ru.team.scheduler.persist.dto.StudentScheduleDto;
import ru.team.scheduler.persist.entities.LessonsStudent;
import springfox.documentation.annotations.ApiIgnore;
import java.security.Principal;
import java.util.List;

@RestController
@Api(tags = {SwaggerConstant.API_USER})
@RequestMapping("/api/v1/students")
public class StudentController {
    private UserService userService;
    private LessonsStudentsServiceImpl lessonsStudentsService;
    private StudentService studentService;
    private UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
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
    public UserDto personalAccount(@ApiIgnore Principal principal){
        return userService.findByEmail(principal.getName()).map(user -> userMapper.entityToDto(user)).orElseThrow(()-> new UsernameNotFoundException("Пользователь не найден в БД !!!"));
    }

    //возвращает все лекции по текущему user(студенту)
    @ApiOperation(value = "Лекции, на которые записан студент", notes = "Список лекций студента")
    @GetMapping("/lessons")
    @Transactional
    public List<StudentScheduleDto> getLessons(@ApiIgnore Principal principal){
        UserDto userDto = userService.findByEmail(principal.getName()).map(user -> userMapper.entityToDto(user)).orElseThrow(()-> new UsernameNotFoundException("Пользователь не найден в БД !!!"));
        return studentService.getScheduleByUser(userDto.getId());
    }

    //возвращает расписание учителя по предоставленной ссылке
    @ApiOperation(value = "Расписание учителя по предоставленной от него ссылке", notes = "Список занятий, запланированных учителем")
    @GetMapping("/lessons/{id}")
    public LessonByIdDto getLessons(@PathVariable Integer id){
        return studentService.getLessonById(id).orElseThrow(NotFoundException::new);
    }

    //удалить запись на урок (освободить место)
    @ApiOperation(value = "Отписаться от урока", notes = "Освободить место в группе")
    @DeleteMapping("/lessons/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Integer id) {
        studentService.deleteLessonsStudentById(id);
    }

    //записаться на лекцию
    @ApiOperation(value = "Записаться на урок", notes = "Добавиться в указанную группу")
    @PostMapping("/lessons/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public LessonsStudentsDto subscribe(@PathVariable("id") Integer lesson_id, Principal principal) {
        LessonsStudent lessonsStudent = studentService.reserveLecture(lesson_id, principal);
        return new LessonsStudentsDto(lessonsStudent);
    }

}
