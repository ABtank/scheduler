package ru.team.scheduler.persist.responsesOfDataBase;


import java.util.Date;

public interface DataToSendNotification {
    String getEmail();
    Integer getLessons_students_id();
    Date getDt_start();
}
