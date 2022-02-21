package ru.team.scheduler.oapi.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.team.scheduler.oapi.dto.transfer.AdminDetails;
import ru.team.scheduler.oapi.dto.transfer.Update;
import ru.team.scheduler.persist.entities.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {
    @NotNull
    private Integer id;
    @Email(groups = {Update.class})
    @NotNull(groups = {Update.class})
    @ApiModelProperty(notes = "Адрес электронной почты", example = "email@mail.com",required = true)
    private String email;
    @ApiModelProperty(notes = "Телефон", example = "+79500309968")
    private String phone;
    @ApiModelProperty(notes = "Фимилия", example = "Иванин")
    private String firstName;
    @ApiModelProperty(notes = "Имя", example = "Ивано")
    private String middleName;
    @ApiModelProperty(notes = "Отчество", example = "Иванинович")
    private String lastName;
    @NotNull(groups = {Update.class})
    @ApiModelProperty(notes = "Роли", example = "[\"ROLE_USER\",\"ROLE_STUDENT\",\"ROLE_TEACHER\",\"ROLE_ADMIN\"]",value = "ROLE_USER",required = true)
    private List<String> roles;
    @JsonView({AdminDetails.class})
    private Date createDate;
    @JsonView({AdminDetails.class})
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

    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.firstName = user.getFirstName();
        this.middleName = user.getMiddleName();
        this.lastName = user.getLastName();
    }
}
