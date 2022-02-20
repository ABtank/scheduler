package ru.team.scheduler.persist.dto;

import java.util.Date;

public interface StudentScheduleDto {
     Integer getLessons_students_id();
     String getLesson_name();
     Date getTime_start();
     String getExercise_name();
     Integer getDuration();
     String getTeacher_first_name();
     String getTeacher_middle_name();
     String getTeacher_last_name();
     String getDiscipline_name();

////            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
////        this.time_start = simpleDateFormat.format(time_start.getTime());

}
