package ru.team.scheduler.oapi.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
