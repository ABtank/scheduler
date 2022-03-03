package ru.team.scheduler.oapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.team.scheduler.persist.entities.TeachersStudent;
import ru.team.scheduler.persist.entities.User;
import ru.team.scheduler.persist.repositories.TeachersStudentsRepository;

import java.util.Optional;

@Service
public class TeachersStudentService {
    TeachersStudentsRepository teachersStudentsRepository;

    @Autowired
    public void setTeachersStudentsRepository(TeachersStudentsRepository teachersStudentsRepository) {
        this.teachersStudentsRepository = teachersStudentsRepository;
    }

    public Optional<TeachersStudent> findByStudentAndTeacher(User student, User teacher){
        return teachersStudentsRepository.findByStudentAndTeacher(student, teacher);
    }

    public TeachersStudent save(User student, User teacher){
        TeachersStudent teachersStudent = new TeachersStudent();
        teachersStudent.setStudent(student);
        teachersStudent.setTeacher(teacher);
        return teachersStudentsRepository.save(teachersStudent);
    }

}
