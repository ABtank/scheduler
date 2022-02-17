package ru.team.scheduler.oapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DisciplineDto {

  private Integer id;
  private String name;

  public DisciplineDto(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public DisciplineDto(String name) {
    this.name = name;
  }
}
