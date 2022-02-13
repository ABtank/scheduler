package ru.team.scheduler.oapi.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class StudentScheduleDto {
    private String lesson_name;
    private Date time_start;
    private String exercise_name;
    private Integer duration;
    private String teacher_name;
    private String discipline_name;

}
