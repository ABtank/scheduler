package ru.team.scheduler.oapi.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.constants.SwaggerConstant;
import ru.team.scheduler.oapi.dto.UserDto;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.oapi.services.UserService;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.List;

@Slf4j
@RequestMapping("/api/v1/users")
@Api(tags = {SwaggerConstant.API_USER})
@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Получить список Пользователей", notes = "Получить список всех зарегистрировавшихся Пользователей.")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Данные получены."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере."),
            @ApiResponse(responseCode = "400", description = "Запрос неверный."),
            @ApiResponse(responseCode = "404", description = "Адрес URL не найден."),
            @ApiResponse(responseCode = "403", description = "Вы не авторизованы. Авторизуйтесь и повторите еще раз."),
            @ApiResponse(responseCode = "401", description = "У вас не достаточно прав доступа."),
    })
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @ApiOperation(value = "Найти Пользователя по id.", notes = "Пользователь.", response = UserDto.class)
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Пользователь найден."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере."),
            @ApiResponse(responseCode = "400", description = "Запрос неверный."),
            @ApiResponse(responseCode = "404", description = "Адрес URL не найден."),
            @ApiResponse(responseCode = "403", description = "Вы не авторизованы. Авторизуйтесь и повторите еще раз."),
            @ApiResponse(responseCode = "401", description = "У вас не достаточно прав доступа."),
    })
    @GetMapping(value = "/{id}")
    public UserDto findById(@PathVariable("id") Integer id) {
        return userService.findById(id).orElseThrow(NotFoundException::new);
    }

    @ApiOperation(value = "Обновить данные Пользователя.", notes = "Обновить основные данные Пользователя.", response = UserDto.class)
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Данные обновлены."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере."),
            @ApiResponse(responseCode = "400", description = "Запрос неверный."),
            @ApiResponse(responseCode = "404", description = "Адрес URL не найден."),
            @ApiResponse(responseCode = "403", description = "Вы не авторизованы. Авторизуйтесь и повторите еще раз."),
            @ApiResponse(responseCode = "401", description = "У вас не достаточно прав доступа."),
    })
    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDTO, @ApiIgnore Principal principal) {
        if (userDTO.getId() == null && userService.checkIsUnique(userDTO.getEmail(), userDTO.getId())) {
            throw new IllegalArgumentException("Id not found in the update request or email not unique");
        }
        return userService.update(userDTO, principal).orElseThrow(NotFoundException::new);
    }

    @ApiIgnore
    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        return new ResponseEntity<>("-=You cannot delete all Users=-", HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Удалить Пользователя.", notes = "Удалить Пользователя (по правде поместить в архив).")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Пользователь удален."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере."),
            @ApiResponse(responseCode = "400", description = "Запрос неверный."),
            @ApiResponse(responseCode = "404", description = "Адрес URL не найден."),
            @ApiResponse(responseCode = "403", description = "Вы не авторизованы. Авторизуйтесь и повторите еще раз."),
            @ApiResponse(responseCode = "401", description = "У вас не достаточно прав доступа."),
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id, @ApiIgnore Principal principal) {
        userService.deleteById(id, principal);
        log.info("-=OK=-");
    }
}