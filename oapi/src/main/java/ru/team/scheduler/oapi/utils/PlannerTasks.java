package ru.team.scheduler.oapi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.team.scheduler.oapi.services.EmailServiceImpl;
import ru.team.scheduler.persist.dto.DataToSendNotification;
import ru.team.scheduler.persist.entities.LessonsStudent;
import ru.team.scheduler.persist.repositories.LessonsStudentsRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class PlannerTasks {
    EmailServiceImpl emailService;
    LessonsStudentsRepository lessonsStudentsRepository;

    @Autowired
    public void setEmailService(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }
    @Autowired
    public void setLessonsStudentsRepository(LessonsStudentsRepository lessonsStudentsRepository) {
        this.lessonsStudentsRepository = lessonsStudentsRepository;
    }

    //получаем список студентов, у которых осталось менее 24 часов до занятия
    // и ещё не отправлено уведомление для подтверждения
    @Scheduled(fixedRate = 600_000)
    @Transactional
    public void getEmailToSendNotification(){
        List<DataToSendNotification> studentsToSendNotification = lessonsStudentsRepository.getStudentsToSendNotification(new Date());
        if(studentsToSendNotification.isEmpty()){
           return;
        }

        //расскомментировать для отправки писем
        for (DataToSendNotification student : studentsToSendNotification) {
            System.out.println(student.getEmail()
                    + " Здравствуйте! Преподаватель просит подтвердить участие в занятии," +
                            "которое состоится " + student.getDt_start());
//            emailService.sendMessage(student.getEmail()
//                    , "Здравствуйте! Преподаватель просит подтвердить участие в занятии," +
//                            "которое состоится " + student.getDt_start());
//            Optional<LessonsStudent> ls = lessonsStudentsRepository.findById(student.getLessons_students_id());
//            if (ls.isPresent()){
//                ls.get().setIsСonfirmationRequestSent(true);
//                lessonsStudentsRepository.save(ls.get());
//            }
        }
    }

}
