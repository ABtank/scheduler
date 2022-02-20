package ru.team.scheduler.oapi.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.config.JwtProvider;
import ru.team.scheduler.oapi.constants.SwaggerConstant;
import ru.team.scheduler.oapi.dto.*;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.oapi.services.SecurityUserService;
import ru.team.scheduler.oapi.services.UserService;
import ru.team.scheduler.persist.entities.User;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
@Api(tags = {SwaggerConstant.API_AUTH})
public class AuthController {
    private SecurityUserService securityUserService;
    private UserService userService;
    private JwtProvider jwtProvider;

    @Autowired
//    @Qualifier("userServiceImpl")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setSecurityUserService(SecurityUserService service) {
        this.securityUserService = service;
    }

    @Autowired
    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @ApiOperation(value = "Авторизоваться.", notes = "Отправить email и пароль для авторизации (для получения Bearer token).", response = DisciplineDto.class)
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto requestDto) {
        User user = securityUserService.getUserByCredentials(requestDto.getEmail(), requestDto.getPassword());
        if (user == null) {
            throw new UsernameNotFoundException("Неверные логин или пароль");
        }
        return new LoginResponseDto(jwtProvider.createToken(user.getEmail()));
    }

    @ApiOperation(value = "Кто я.", notes = "Информация авторизовавшегося пользователя", response = DisciplineDto.class)
    @GetMapping("/user")
    public UserResponseDto login(Principal principal) {
        User user = securityUserService.getUserByEmail(principal.getName());
        return new UserResponseDto(new UserDto(user));
    }

    @ApiOperation(value = "Зарегистрировать нового Пользователя.", notes = "Зарегистрировать нового Пользователя (учитель, студент).", response = DisciplineDto.class)
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Новый Пользователь была зарегистрирован."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере."),
            @ApiResponse(responseCode = "400", description = "Запрос неверный."),
            @ApiResponse(responseCode = "404", description = "Адрес URL не найден."),
            @ApiResponse(responseCode = "403", description = "Вы не авторизованы. Авторизуйтесь и повторите еще раз."),
            @ApiResponse(responseCode = "401", description = "У вас не достаточно прав доступа."),
    })
    @PostMapping
    public UserDto create(@RequestBody UserCreationDto userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getMatchingPassword())) {
            throw new IllegalArgumentException("password not matching");
        }
        return userService.save(userDTO).orElseThrow(NotFoundException::new);
    }
}
