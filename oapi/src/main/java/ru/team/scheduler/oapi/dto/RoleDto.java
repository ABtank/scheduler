package ru.team.scheduler.oapi.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.team.scheduler.oapi.dto.transfer.New;
import ru.team.scheduler.oapi.dto.transfer.Update;
import ru.team.scheduler.persist.entities.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
public class RoleDto {

    @Null(groups = {New.class})
    @NotNull(groups = {Update.class})
    private Integer id;

    @NonNull()
    @NotNull(groups = {New.class, Update.class})
    @ApiModelProperty(notes = "Уникальное название роли", example = "ROLE_USER",required = true)
    private String name;

    @NotNull(groups = {New.class, Update.class})
    private String description;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.description =role.getDescription();
    }
}