package ru.team.scheduler.oapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class LessonsStudentsDto {

  private Integer id;
  private Integer lessonId;
  private String lesson;
  private Integer studentId;
  private String studentFirstName;
  private Boolean isAttend;
  private Date dtCreate;
  private Date dtModify;

}
