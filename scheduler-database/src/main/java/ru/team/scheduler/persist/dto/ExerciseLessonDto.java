package ru.team.scheduler.persist.dto;

import java.util.Date;

public interface ExerciseLessonDto {
    Integer getExerciseId();
    Integer getExerciseDuration();
    Integer getLessonId();
    String getLessonName();
    Date getLessonsDt_start();
    String getDisciplineName();
    String getTeacherFullName();
    String getStudentEmail();
}
