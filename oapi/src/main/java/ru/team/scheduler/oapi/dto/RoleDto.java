package ru.team.scheduler.oapi.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.team.scheduler.persist.entities.Role;

@Data
@NoArgsConstructor
public class RoleDto {
    private Integer id;
    @NonNull
    private String name;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }
}