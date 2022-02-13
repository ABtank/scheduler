package ru.team.scheduler.oapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DisciplineDto {

  private String name;

  public DisciplineDto(String name) {
    this.name = name;
  }
}
