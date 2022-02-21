package ru.team.scheduler.oapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import java.util.List;

@Data
@NoArgsConstructor
public class UserCreationDto {
    @NonNull
    @Email
    @ApiModelProperty(notes = "Адрес электронной почты", example = "email@mail.com",required = true)
    private String email;
    @NonNull
    @ApiModelProperty(required = true, example = "qwerty")
    private String password;
    @NonNull
    @ApiModelProperty(required = true, example = "qwerty")
    private String matchingPassword;

    @NonNull
    @ApiModelProperty(notes = "Роли", example = "[\"ROLE_USER\",\"ROLE_STUDENT\",\"ROLE_TEACHER\",\"ROLE_ADMIN\"]",value = "ROLE_USER",required = true)
    private List<String> roles;

    public UserCreationDto(String email, String password, String matchingPassword, List<String> roles) {
        this.email = email;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.roles = roles;
    }
}