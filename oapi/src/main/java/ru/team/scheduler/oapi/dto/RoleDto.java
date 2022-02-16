package ru.team.scheduler.oapi.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.team.scheduler.persist.entities.Role;

@Data
@NoArgsConstructor
public class RoleDto {
    private Integer id;
    @NonNull
    @ApiModelProperty(notes = "Уникальное название роли", example = "ROLE_USER",required = true)
    private String name;

    private String description;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.description =role.getDescription();
    }
}