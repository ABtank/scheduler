package ru.team.scheduler.oapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.team.scheduler.oapi.constants.CRUD;
import ru.team.scheduler.oapi.dto.UserDto;
import ru.team.scheduler.oapi.exceptions.CrudException;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.persist.dto.LessonByIdDto;
import ru.team.scheduler.persist.dto.StudentScheduleDto;
import ru.team.scheduler.persist.entities.Lesson;
import ru.team.scheduler.persist.entities.LessonsStudent;
import ru.team.scheduler.persist.entities.TeachersStudent;
import ru.team.scheduler.persist.entities.User;
import ru.team.scheduler.persist.repositories.LessonsStudentsRepository;
import ru.team.scheduler.persist.repositories.StudentRepository;
import ru.team.scheduler.persist.repositories.LessonRepository;
import ru.team.scheduler.persist.repositories.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService extends UserServiceImpl {
    private StudentRepository studentRepository;
    private LessonsStudentsRepository lessonsStudentRepository;
    private LessonRepository lessonRepository;
    private TeachersStudentService teachersStudentService;

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
    public void setTeachersStudentService(TeachersStudentService teachersStudentService) {
        this.teachersStudentService = teachersStudentService;
    }

    public List<StudentScheduleDto> getScheduleByUser(Integer user_id) {
        return studentRepository.getUserSchedule(user_id);
    }

    public Optional<LessonByIdDto> getLessonById(Integer id) {
        return lessonRepository.getLessonById(id);
    }

    @Transactional
    public void deleteLessonsStudentById(Integer id) {
        Optional<LessonsStudent> lessonsStudent = lessonsStudentRepository.findById(id);
        lessonsStudentRepository.deleteById(id);
    }

    @Transactional
    public LessonsStudent reserveLecture(Integer lesson_id, Principal principal) {
        User user = findByEmail(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден в БД !!!"));
        Optional findLessonStudent = lessonsStudentRepository.findByLessonIdAndStudentId(lesson_id, user.getId());
        if (!findLessonStudent.isEmpty()) {
            throw new CrudException("lessons_students", CRUD.CREATE, lesson_id, "Лекция уже добавлена");
        }
        Optional<Lesson> lesson = lessonRepository.findById(lesson_id);
        if (lesson.isPresent()) {
            LessonsStudent lessonsStudent = new LessonsStudent();
            lessonsStudent.setLesson(lesson.get());
            lessonsStudent.setStudent(user);
            lessonsStudent.setDtCreate();
            lessonsStudent.setDtModify();

            //проверка на существование и запись в таблицу teachers_students
            User teacher = lesson.get().getExercise().getTeacher();
            Optional<TeachersStudent> recordStudentAndTeacher = teachersStudentService.findByStudentAndTeacher(user, teacher);
            if (recordStudentAndTeacher.isEmpty()){
                teachersStudentService.save(user, teacher);
            }

            return lessonsStudentRepository.save(lessonsStudent);
        }

        throw new NotFoundException(
                Lesson.class.getName(),
                lesson_id,
                "Не найдена лекция по id = " + lesson_id);
    }
}
