package ru.team.scheduler.oapi.dto.lesson;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.team.scheduler.oapi.dto.transfer.New;
import ru.team.scheduler.oapi.dto.transfer.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonDto {

  @NotNull(groups = {Update.class})
  @Null(groups = {New.class})
  private Integer id;

  @ApiModelProperty(notes = "название занятия , если пусто, то ставится название урока", example = "Урок физики 1", required = true)
  @NotNull(groups = {New.class, Update.class})
  private String lesson;

  @ApiModelProperty(notes = "ссылка на занятие, если есть")
  private String link;

  @ApiModelProperty(notes = "id урока")
  @NotNull(groups = {New.class, Update.class})
  private Integer exerciseId;

  @ApiModelProperty(notes = "название урока")
  private String exercise;

  @ApiModelProperty(notes = "время и дата начала занятия")
  @NotNull(groups = {New.class})
  private String dtStart;

  public LessonDto(String lesson, String dtStart) {
    this.lesson= lesson;
    this.dtStart = dtStart;
  }
}
