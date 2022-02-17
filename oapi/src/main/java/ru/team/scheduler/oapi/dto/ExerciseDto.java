package ru.team.scheduler.oapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDto {
  @ApiModelProperty(required = true)
  private Integer id;
  @ApiModelProperty(notes = "название урока/курса, если пусто, то ставится название дисциплины", example = "Физика ч.1",required = true)
  private String name;
  @ApiModelProperty(notes = "персоналка или нет. По умолчанию персоналка")
  private Boolean isPersonal;
  @ApiModelProperty(notes = "Продолжительность урока", example = "45",required = true)
  private Integer duration;
  @ApiModelProperty(notes = "Если не персоналка, то указывается кол-во народу. По умолчанию 1.", example = "30")
  private Integer quantity;
  @ApiModelProperty(notes = "id Учителя",required = true)
  private Integer teacherId;
  @ApiModelProperty(notes = "ФИО учителя")
  private String teacher;
  @ApiModelProperty(notes = "id Дисциплины",required = true)
  private Integer disciplineId;
  @ApiModelProperty(notes = "Название Дисциплины")
  private String discipline;


}
