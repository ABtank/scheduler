package ru.team.scheduler.persist.dto;

import java.util.Date;

public interface LessonByIdDto {
    Integer getLessons_id();
    String getLesson_name();
    Date getTime_start();
    Integer getDuration();
    String getTeacher_first_name();
    String getTeacher_middle_name();
    String getTeacher_last_name();
    String getDiscipline_name();

}
