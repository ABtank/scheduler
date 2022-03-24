package ru.team.scheduler.oapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TeachersStudentsDto {

  private Integer id;
  private Integer teacherId;
  private String teacherFirstName;
  private Integer studentId;
  private String studentFirstName;
  private Boolean archive;
  private String dtCreate;
  private Date dtModify;

}
