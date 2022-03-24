package ru.team.scheduler.persist.responsesOfDataBase;

import java.util.Date;

public interface ExerciseLessonResponse {
    Integer getExerciseId();
    Integer getExerciseDuration();
    Integer getLessonId();
    String getLessonName();
    Date getLessonsDtstart();
    String getDisciplineName();
    String getTeacherFullName();
    String getStudentEmail();
}
