package ru.team.scheduler.oapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.team.scheduler.persist.dto.LessonByIdDto;
import ru.team.scheduler.persist.dto.StudentScheduleDto;
import ru.team.scheduler.persist.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentScheduleDto> getScheduleByUser(Integer user_id) {
        return studentRepository.getUserSchedule(user_id);
    }

    public Optional<LessonByIdDto> getLessonById(Integer id){
        return studentRepository.getLessonById(id);
    }
}
