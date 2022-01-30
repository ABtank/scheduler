package ru.team.scheduler.oapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String email;
    private String phone;
    private String firstName;
    private String middleName;
    private String lastName;
    private List<String> roles;
    private Date createDate;
    private Date modifyDate;

    public UserDto(Integer id, String email, String phone, String firstName, String middleName, String lastName, List<String> roles, Date createDate, Date modifyDate) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.roles = roles;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }
}
