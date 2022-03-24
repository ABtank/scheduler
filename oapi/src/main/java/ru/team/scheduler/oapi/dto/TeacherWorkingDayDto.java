package ru.team.scheduler.oapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.team.scheduler.oapi.dto.transfer.New;
import ru.team.scheduler.oapi.dto.transfer.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherWorkingDayDto {

    @ApiModelProperty()
    @Null(groups = {New.class})
    @NotNull(groups = {Update.class})
    private Integer id;

    @ApiModelProperty(notes = "id дня недели", required = true)
    @NotNull(groups = {Update.class, New.class})
    private Integer weekdayId;

    @ApiModelProperty(notes = "id лекции/урока", required = true)
    @NotNull(groups = {Update.class, New.class})
    private Integer exerciseId;

    @ApiModelProperty(notes = "название дня недели", example = "Понедельник")
    private String weekDayName;

    @ApiModelProperty(notes = "название урока/курса", example = "Физика ч.1")
    private String exerciseName;

    @ApiModelProperty (notes = "Продолжительность перемены")
    @NotNull(groups = {Update.class, New.class})
    private Integer breakDuration;

    @ApiModelProperty (notes = "Количество уроков в день")
    @NotNull(groups = {Update.class, New.class})
    private Integer lessonsQuantity;

    @ApiModelProperty(notes = "время начала рабочего дня по данному уроку", example = "09:00", required = true)
    @NotNull(groups = {Update.class, New.class})
    private String timeStart;

    @ApiModelProperty(notes = "время окончания рабочего дня по данному уроку")
    @Null(groups = {Update.class, New.class})
    private String timeEnd;
}
