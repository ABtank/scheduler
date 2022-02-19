package ru.team.scheduler.oapi.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDto {

  @ApiModelProperty(required = true)
  @Null(groups = {New.class})
  @NotNull(groups = {Update.class})
  @JsonView({AdminDetails.class})
  private Integer id;

  @ApiModelProperty(notes = "название урока/курса, если пусто, то ставится название дисциплины", example = "Физика ч.1",required = true)
  @NotNull(groups = {Update.class, New.class})
  @JsonView({Details.class, AdminDetails.class})
  private String name;

  @ApiModelProperty(notes = "персоналка или нет. По умолчанию персоналка")
  @NotNull(groups = {Update.class, New.class})
  @JsonView({Details.class, AdminDetails.class})
  private Boolean isPersonal;

  @ApiModelProperty(notes = "Продолжительность урока", example = "45",required = true)
  @NotNull(groups = {Update.class, New.class})
  @JsonView({Details.class, AdminDetails.class})
  private Integer duration;

  @ApiModelProperty(notes = "Если не персоналка, то указывается кол-во народу. По умолчанию 1.", example = "30")
  @NotNull(groups = {Update.class, New.class})
  @JsonView({Details.class, AdminDetails.class})
  private Integer quantity;

  @ApiModelProperty(notes = "id Учителя",required = true)
  @Null(groups = {New.class})
  @NotNull(groups = {Update.class})
  @JsonView({AdminDetails.class})
  private Integer teacherId;

  @ApiModelProperty(notes = "ФИО учителя")
  @Null(groups = {New.class})
  @NotNull(groups = {Update.class})
  @JsonView({Details.class, AdminDetails.class})
  private String teacher;

  @ApiModelProperty(notes = "id Дисциплины",required = true)
  @Null(groups = {New.class})
  @NotNull(groups = {Update.class})
  @JsonView({AdminDetails.class})
  private Integer disciplineId;

  @ApiModelProperty(notes = "Название Дисциплины")
  @NotNull(groups = {Update.class, New.class})
  @JsonView({Details.class, AdminDetails.class})
  private String discipline;
}
