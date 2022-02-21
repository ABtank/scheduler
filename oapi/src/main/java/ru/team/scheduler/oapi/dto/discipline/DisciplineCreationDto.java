package ru.team.scheduler.oapi.dto.discipline;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.team.scheduler.oapi.dto.transfer.New;
import ru.team.scheduler.oapi.dto.transfer.Update;

import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
public class DisciplineCreationDto {
    @NotNull(groups = {New.class, Update.class})
    @ApiModelProperty(notes = "Уникальное название дисциплины", example = "Прогаммирование", required = true)
    private String name;

}
