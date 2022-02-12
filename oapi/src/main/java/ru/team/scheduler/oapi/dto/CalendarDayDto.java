package ru.team.scheduler.oapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class CalendarDayDto {
    private Date dayDate;
    private String weekDay;
    private Boolean isHoliday;
}
