package ru.team.scheduler.persist.responsesOfDataBase;

import java.util.Date;

public interface LessonByIdResponse {
    Integer getLessonsId();
    String getLessonName();
    Date getTimeStart();
    Integer getDuration();
    String getTeacherFirstName();
    String getTeacherMiddleName();
    String getTeacherLastName();
    String getDisciplineName();
}
