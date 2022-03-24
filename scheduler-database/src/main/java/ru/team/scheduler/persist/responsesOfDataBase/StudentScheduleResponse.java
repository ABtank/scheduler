package ru.team.scheduler.persist.responsesOfDataBase;

import java.util.Date;

public interface StudentScheduleResponse {
     Integer getLessonsStudentsId();
     String getLessonName();
     Date getTimeStart();
     String getExerciseName();
     Integer getDuration();
     String getTeacherFirstName();
     String getTeacherMiddleName();
     String getTeacherLastName();
     String getDisciplineName();
     boolean getIsValidate();
     boolean getIsAccepted();

////            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
////        this.time_start = simpleDateFormat.format(time_start.getTime());

}
