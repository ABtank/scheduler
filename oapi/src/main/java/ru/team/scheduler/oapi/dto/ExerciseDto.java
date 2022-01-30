package ru.team.scheduler.oapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExerciseDto {

  private Integer id;
  private String name;
  private Boolean isPersonal;
  private Integer duration;
  private Integer quantity;
  private Integer teacherId;
  private String teacher;
  private Integer disciplineId;
  private String discipline;


}
