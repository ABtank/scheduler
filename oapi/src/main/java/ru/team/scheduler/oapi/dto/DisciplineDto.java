package ru.team.scheduler.oapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DisciplineDto {

  private long id;
  private String name;

  public DisciplineDto(long id, String name) {
    this.id = id;
    this.name = name;
  }
}
