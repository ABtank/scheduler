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
import ru.team.scheduler.oapi.dto.UserDto;
import ru.team.scheduler.oapi.services.UserServiceImpl;
import ru.team.scheduler.persist.entities.User;

import java.security.Principal;

@RestController
@Api(tags = {SwaggerConstant.API_USER})
@RequestMapping("/api/v1/students")
public class StudentController {
    private UserServiceImpl userServiceImpl;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userServiceImpl = userService;
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
        User user = userServiceImpl.findByName("иван").orElseThrow(()-> new UsernameNotFoundException("Пользователь не найден в БД !!!"));
        return new UserDto(user);
    }
}
