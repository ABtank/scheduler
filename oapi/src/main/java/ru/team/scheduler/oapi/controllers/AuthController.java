package ru.team.scheduler.oapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.config.JwtProvider;
import ru.team.scheduler.oapi.dto.*;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.oapi.services.SecurityUserService;
import ru.team.scheduler.oapi.services.UserService;
import ru.team.scheduler.persist.entities.User;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private SecurityUserService securityUserService;
    private UserService userService;
    private JwtProvider jwtProvider;

    @Autowired
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

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto requestDto) {
        User user = securityUserService.getUserByCredentials(requestDto.getEmail(), requestDto.getPassword());
        if (user == null) {
            throw new UsernameNotFoundException("Неверные логин или пароль");
        }

        return new LoginResponseDto(jwtProvider.createToken(user.getEmail()));
    }

    @GetMapping("/user")
    public UserResponseDto login(Principal principal) {
        User user = securityUserService.getUserByEmail(principal.getName());

        return new UserResponseDto(new UserDto(user));
    }

    @PostMapping
    public UserDto create(@RequestBody UserCreationDto userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getMatchingPassword())) {
            throw new IllegalArgumentException("password not matching");
        }
        return userService.save(userDTO).orElseThrow(NotFoundException::new);
    }
}
