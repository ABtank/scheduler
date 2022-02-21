package ru.team.scheduler.oapi.dto;

import lombok.Data;

@Data
public class RegistrationRequestDto { //TODO валидатор
    private String email;
    private String last_name;
    private String first_name;
    private String middle_name;
    private String phone;
    private String user_role;
    private String password;
    private String password_confirmation;
}
