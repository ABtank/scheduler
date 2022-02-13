package ru.team.scheduler.oapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.team.scheduler.persist.entities.LessonsStudent;

import java.util.Date;

@Data
@NoArgsConstructor
public class LessonsStudentsDto {

  private Integer id;
  private Integer lessonId;
  private Integer studentId;
  private String studentFirstName;
  private String studentMiddleName;
  private String studentLastName;
  private Boolean isAttend;
  private Date dtCreate;
  private Date dtModify;

  public LessonsStudentsDto(LessonsStudent lessonsStudent) {
    this.id = lessonsStudent.getId();
    this.lessonId = lessonsStudent.getLesson().getId();
    this.studentId = lessonsStudent.getStudent().getId();
    this.studentFirstName = lessonsStudent.getStudent().getFirstName();
    this.studentMiddleName = lessonsStudent.getStudent().getMiddleName();
    this.studentLastName = lessonsStudent.getStudent().getLastName();
    this.isAttend = lessonsStudent.getIsAttend();
    this.dtCreate = lessonsStudent.getDtCreate();
    this.dtModify = lessonsStudent.getDtModify();
  }
}
