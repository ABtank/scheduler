package ru.team.scheduler.oapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private List<String> roles;
    private Date createDate;
    private Date modifyDate;

    public UserDto(Integer id, String name, String phone, String email, List<String> roles, Date createDate, Date modifyDate) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.roles = roles;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }
}
