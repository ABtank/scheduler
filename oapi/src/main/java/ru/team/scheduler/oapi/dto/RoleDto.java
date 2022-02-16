package ru.team.scheduler.oapi.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class RoleDto {
    private Integer id;
    @NonNull
    @ApiModelProperty(notes = "Уникальное название роли", example = "ROLE_USER",required = true)
    private String name;
}