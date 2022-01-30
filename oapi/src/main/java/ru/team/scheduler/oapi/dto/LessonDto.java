package ru.team.scheduler.oapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class LessonDto {

  private Integer id;
  private String name;
  private String link;
  private Integer exerciseId;
  private String exercise;
  private Date dtStart;

}
