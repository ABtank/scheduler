package ru.team.scheduler.oapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginRequestDto {
    @ApiModelProperty(notes = "Unique Email address", required = true, example = "email@mail.com")
    private String email;
    @ApiModelProperty(required = true, example = "123")
    private String password;
}
