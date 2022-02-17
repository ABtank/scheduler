package ru.team.scheduler.oapi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.config.JwtProvider;
import ru.team.scheduler.oapi.dto.LoginRequestDto;
import ru.team.scheduler.oapi.dto.LoginResponseDto;
import ru.team.scheduler.oapi.services.SecurityUserService;
import ru.team.scheduler.persist.entities.User;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private SecurityUserService userService;
    private JwtProvider jwtProvider;

    @Autowired
    public void setUserService(SecurityUserService service) {
        this.userService = service;
    }

    @Autowired
    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto requestDto) {
        User user = userService.getUserByCredentials(requestDto.getEmail(), requestDto.getPassword());
        if (user == null) {
            throw new UsernameNotFoundException("Неверные логин или пароль");
        }

        return new LoginResponseDto(jwtProvider.createToken(user.getEmail()));
    }
}
