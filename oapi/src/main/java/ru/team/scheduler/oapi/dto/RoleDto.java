package ru.team.scheduler.oapi.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class RoleDto {
    private Integer id;
    @NonNull
    private String name;
}