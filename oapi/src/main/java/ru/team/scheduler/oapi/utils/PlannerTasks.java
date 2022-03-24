package ru.team.scheduler.oapi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.team.scheduler.oapi.services.EmailServiceImpl;
import ru.team.scheduler.persist.responsesOfDataBase.DataToSendNotification;
import ru.team.scheduler.persist.responsesOfDataBase.ExerciseLessonResponse;
import ru.team.scheduler.persist.repositories.ExercisesRepository;
import ru.team.scheduler.persist.repositories.LessonsStudentsRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class PlannerTasks {
    EmailServiceImpl emailService;
    LessonsStudentsRepository lessonsStudentsRepository;
    ExercisesRepository exercisesRepository;

    @Autowired
    public void setEmailService(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }
    @Autowired
    public void setLessonsStudentsRepository(LessonsStudentsRepository lessonsStudentsRepository) {
        this.lessonsStudentsRepository = lessonsStudentsRepository;
    }
    @Autowired
    public void setExercisesRepository(ExercisesRepository exercisesRepository) {
        this.exercisesRepository = exercisesRepository;
    }


    //получаем список студентов, у которых осталось менее 24 часов до занятия
    // и ещё не отправлено уведомление для подтверждения и рассылаем им запрос присутствия на занятии
    @Scheduled(fixedRate = 600_000)
    @Transactional
    public void sendRequestForConfirmationOfAttendanceAtLectureByMail(){
        List<DataToSendNotification> studentsToSendNotification = lessonsStudentsRepository.getStudentsToSendNotification(new Date());
        if(studentsToSendNotification.isEmpty()){
           return;
        }

        //расскомментировать для отправки писем
        for (DataToSendNotification student : studentsToSendNotification) {
            System.out.println(student.getEmail()
                    + " Здравствуйте! Преподаватель просит подтвердить участие в занятии," +
                            "которое состоится " + student.getDt_start());
//            emailService.sendSimpleMessage(student.getEmail(), "Подтверждение присутствия на занятии"
//                    , "Здравствуйте! Преподаватель просит подтвердить участие в занятии," +
//                            "которое состоится " + student.getDt_start());
//            Optional<LessonsStudent> ls = lessonsStudentsRepository.findById(student.getLessons_students_id());
//            if (ls.isPresent()){
//                ls.get().setIsСonfirmationRequestSent(true);
//                lessonsStudentsRepository.save(ls.get());
//            }
        }
    }

    //проверяем каждые 10 мин, есть ли изменения в расписании (exercise)
    // и отправляем результат на почту студентам, которые подписаны
    //после изменения должно пройти от 60 до 73 минут (если преподаватель надумает в течение часа что-то поменять ещё в записи)
    @Scheduled(fixedRate = 600_000)
    public void sendNotificationOfScheduleChangesByMail(){
        List<ExerciseLessonResponse> exerciseLessonResponseList = exercisesRepository.getLastExercisesChanges();
        for (ExerciseLessonResponse elDto : exerciseLessonResponseList) {
            String theme = "Изменения в расписании";
            StringBuilder text = new StringBuilder();
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm d-MM-yyyy");
            text.append("Произошли изменения в расписании по предмету ").append(elDto.getDisciplineName())
                    .append(", преподаватель - ").append(elDto.getTeacherFullName()).append(". ")
                    .append("Занятие ").append("'").append(elDto.getLessonName()).append("'")
                    .append(" состоится в " + dateFormat.format(elDto.getLessonsDtstart())).append(".");
            //emailService.sendSimpleMessage(new String[]{elDto.getStudentEmail()}, theme, text.toString());
            System.out.println(text);
        }
    }

}
