package ru.team.scheduler.oapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherWorkingDayDto {
    private Integer id;
    private Integer weekdayId;
    private Integer exerciseId;
    private String weekDayName;
    private String exerciseName;
    private String timeStart;
    private String timeEnd;
}
