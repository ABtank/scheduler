package ru.team.scheduler.oapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.team.scheduler.persist.entities.Exercise;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDto {
  private Integer id;
  private String name;
  private Integer disciplineId;
  private Boolean isPersonal;
  private Integer duration;
  private Integer quantity;
  private Integer teacherId;
  private String teachersFirstName;
  private String teachersLastName;
  private String disciplineTitle;
}
