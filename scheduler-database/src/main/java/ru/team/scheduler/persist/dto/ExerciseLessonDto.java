package ru.team.scheduler.persist.dto;

import java.util.Date;

public interface ExerciseLessonDto {
    Integer getExerciseId();
    Integer getExerciseDuration();
    Integer getLessonId();
    String getLessonName();
    Date getLessonsDtstart();
    String getDisciplineName();
    String getTeacherFullName();
    String getStudentEmail();
}
