package ru.team.scheduler.oapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.team.scheduler.oapi.dto.StudentScheduleDto;
import ru.team.scheduler.persist.repositories.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentScheduleDto> getScheduleByUser(Integer user_id) {
        return studentRepository.getScheduleByUser(user_id);
    }
}
