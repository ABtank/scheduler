package ru.team.scheduler.persist.dto;

import lombok.Data;
import ru.team.scheduler.persist.responsesOfDataBase.LessonByIdResponse;

import java.util.Date;

@Data
public class LessonByIdDto {
    Integer lessonsId;
    String lessonName;
    Date timeStart;
    Integer duration;
    String teacherFirstName;
    String teacherMiddleName;
    String teacherLastName;
    String disciplineName;
    Boolean studentRegistered;

    public LessonByIdDto(LessonByIdResponse lessonByIdResponse) {
        this.lessonsId = lessonByIdResponse.getLessonsId();
        this.lessonName = lessonByIdResponse.getLessonName();
        this.timeStart = lessonByIdResponse.getTimeStart();
        this.duration = lessonByIdResponse.getDuration();
        this.teacherFirstName = lessonByIdResponse.getTeacherFirstName();
        this.teacherMiddleName = lessonByIdResponse.getTeacherMiddleName();
        this.teacherLastName = lessonByIdResponse.getTeacherLastName();
        this.disciplineName = lessonByIdResponse.getDisciplineName();
        this.studentRegistered = false;
    }
}
