package ru.team.scheduler.oapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DisciplineDto {

  private Integer id;
  @ApiModelProperty(notes = "Уникальное название дисциплины", example = "Прогаммирование",required = true)
  private String name;

  public DisciplineDto(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public DisciplineDto(String name) {
    this.name = name;
  }
}
