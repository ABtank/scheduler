package ru.team.scheduler.persist.dto;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class LessonByIdDto {
    private Integer lessons_id;
    private String lesson_name;
    //    private Date time_start;
    private String time_start;
    private String exercise_name;
    private Integer duration;
    private String teacher_name;
    private String discipline_name;

    public LessonByIdDto(Integer lessons_id, String lesson_name, Date time_start, String exercise_name, Integer duration, String teacher_name, String discipline_name) {
        this.lessons_id = lessons_id;
        this.lesson_name = lesson_name;
        this.exercise_name = exercise_name;
        this.duration = duration;
        this.teacher_name = teacher_name;
        this.discipline_name = discipline_name;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        this.time_start = simpleDateFormat.format(time_start.getTime());
    }
}
