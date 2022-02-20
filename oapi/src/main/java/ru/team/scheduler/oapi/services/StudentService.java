package ru.team.scheduler.oapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.team.scheduler.oapi.dto.UserDto;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.persist.dto.LessonByIdDto;
import ru.team.scheduler.persist.dto.StudentScheduleDto;
import ru.team.scheduler.persist.entities.Lesson;
import ru.team.scheduler.persist.entities.LessonsStudent;
import ru.team.scheduler.persist.entities.User;
import ru.team.scheduler.persist.repositories.LessonsStudentsRepository;
import ru.team.scheduler.persist.repositories.StudentRepository;
import ru.team.scheduler.persist.repositories.LessonRepository;
import ru.team.scheduler.persist.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private LessonsStudentsRepository lessonsStudentRepository;
    private LessonRepository lessonRepository;
    private UserRepository userRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Autowired
    public void setLessonsStudentRepository(LessonsStudentsRepository lessonsStudentRepository) {
        this.lessonsStudentRepository = lessonsStudentRepository;
    }
    @Autowired
    public void setLessonRepository(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<StudentScheduleDto> getScheduleByUser(Integer user_id) {
        return studentRepository.getUserSchedule(user_id);
    }

    public Optional<LessonByIdDto> getLessonById(Integer id){
        return studentRepository.getLessonById(id);
    }

    @Transactional
    public void deleteLessonById(Integer id){
        Optional<LessonsStudent> lessonsStudent = lessonsStudentRepository.findById(id);
        //добавить поле booked в LessonsStudent и снять бронь
//        lessonsStudent.get().getLesson().setBooked(false);
        lessonsStudentRepository.deleteById(id);
    }

    @Transactional
    public List<StudentScheduleDto> reserveLecture(Integer lesson_id, UserDto userDto) {
        Optional<Lesson> lesson = lessonRepository.findById(lesson_id);
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());
        if (lesson.isPresent() & user.isPresent()) {
            LessonsStudent lessonsStudent = new LessonsStudent();
            lessonsStudent.setLesson(lesson.get());
            lessonsStudent.setStudent(user.get());
            lessonsStudent.setDtCreate();
            lessonsStudent.setDtModify();
            lessonsStudentRepository.save(lessonsStudent);
            return getScheduleByUser(user.get().getId());
        }

        throw new NotFoundException(
                Lesson.class.getName(),
                lesson_id,
                "Не найдена лекция по id = " + lesson_id);
    }
}
