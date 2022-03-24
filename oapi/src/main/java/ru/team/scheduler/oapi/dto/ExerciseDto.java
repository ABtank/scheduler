package ru.team.scheduler.oapi.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.team.scheduler.oapi.dto.transfer.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDto {

    // не знаю как указать тут required - если он нужен только для Update.class
    //тоже самое касается всех остальных полей , обязательность заполнения которых разная при update и new
    @ApiModelProperty()
    @Null(groups = {New.class})
    @NotNull(groups = {Update.class})
    @JsonView({AdminDetails.class})
    private Integer id;

    @ApiModelProperty(notes = "название урока/курса, если пусто, то ставится название дисциплины", example = "Физика ч.1", required = true)
    @NotNull(groups = {Update.class, New.class})
    @JsonView({Details.class, AdminDetails.class})
    private String name;

    @ApiModelProperty(notes = "персоналка или нет. По умолчанию персоналка")
    @NotNull(groups = {New.class, Update.class})
    @JsonView({Details.class, AdminDetails.class})
    private Boolean isPersonal;

    @ApiModelProperty(notes = "Продолжительность урока", example = "45")
    @NotNull(groups = {New.class, Update.class})
    @JsonView({Details.class, AdminDetails.class})
    private Integer duration;

    @ApiModelProperty(notes = "Если не персоналка, то указывается кол-во народу. По умолчанию 1.", example = "30")
    @NotNull(groups = {Update.class, New.class})
    @JsonView({Details.class, AdminDetails.class})
    private Integer quantity;

    @ApiModelProperty(notes = "id Учителя")
    @Null(groups = {New.class})
    @NotNull(groups = {Update.class})
    @JsonView({AdminDetails.class})
    private Integer teacherId;

    @ApiModelProperty(notes = "ФИО учителя")
    @Null(groups = {New.class})
    @NotNull(groups = {Update.class})
    @JsonView({Details.class, AdminDetails.class})
    private String teacher;

    @ApiModelProperty(notes = "id Дисциплины")
    @NotNull(groups = {Update.class, New.class})
    @JsonView({AdminDetails.class})
    private Integer disciplineId;

    @ApiModelProperty(notes = "Название Дисциплины")
    @NotNull(groups = {Update.class, New.class})
    @JsonView({Details.class, AdminDetails.class})
    private String discipline;

    @ApiModelProperty(notes = "Дата последнего изменения расписания")
    @Null(groups = {Update.class, New.class})
    @JsonView({Details.class, AdminDetails.class})
    private Date dtModify;
}
