package ru.team.scheduler.oapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
public class UserCreationDto {
    private Integer id;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String matchingPassword;

    @NonNull
    private List<String> roles;

    public UserCreationDto(Integer id, String email, String password, String matchingPassword, List<String> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.roles = roles;
    }
}