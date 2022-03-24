package ru.team.scheduler.oapi.dto.discipline;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.team.scheduler.oapi.dto.transfer.New;
import ru.team.scheduler.oapi.dto.transfer.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
public class DisciplineDto {

    @Null(groups = {New.class})
    @NotNull(groups = {Update.class})
    private Integer id;

    @NotNull(groups = {New.class, Update.class})
    @ApiModelProperty(notes = "Уникальное название дисциплины", example = "Прогаммирование", required = true)
    private String name;

    public DisciplineDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}
