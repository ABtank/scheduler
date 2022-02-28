package ru.team.scheduler.oapi.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.team.scheduler.oapi.dto.ExerciseDto;
import ru.team.scheduler.oapi.dto.LessonsStudentsDto;
import ru.team.scheduler.oapi.dto.TeacherWorkingDayDto;
import ru.team.scheduler.oapi.dto.lesson.LessonDto;
import ru.team.scheduler.oapi.utils.DateFormatter;
import ru.team.scheduler.persist.entities.Exercise;
import ru.team.scheduler.persist.entities.Lesson;
import ru.team.scheduler.persist.entities.LessonsStudent;
import ru.team.scheduler.persist.entities.TeacherWorkingDay;
import ru.team.scheduler.persist.repositories.*;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class MapperService {
    private final WeekdaysRepository weekdaysRepository;
    private final ExercisesRepository exercisesRepository;
    private final DateFormatter dateFormatter;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    public ExerciseDto exerciseToDto(Exercise exercise) {
        return new ExerciseDto(
                exercise.getId(),
                exercise.getName(),
                exercise.getIsPersonal(),
                exercise.getDuration(),
                exercise.getQuantity(),
                exercise.getTeacher().getId(),
                exercise.getTeacher().getFirstName() + " " + exercise.getTeacher().getMiddleName() + " " +
                exercise.getTeacher().getLastName(),
                exercise.getDiscipline().getId(),
                exercise.getDiscipline().getName()
        );
    }

    public Exercise exerciseDTOtoExercise(ExerciseDto exerciseDto) {
        return new Exercise(
                exerciseDto.getId(),
                exerciseDto.getName(),
                exerciseDto.getIsPersonal(),
                exerciseDto.getDuration(),
                exerciseDto.getQuantity()
        );
    }

    public TeacherWorkingDay teacherWorkingDayDtoToTeacherWorkingDay(TeacherWorkingDayDto teacherWorkingDaysDto) {
        Date dateStart = null;
        Date dateEnd = null;
        try {
            dateStart = new SimpleDateFormat("hh:mm").parse(teacherWorkingDaysDto.getTimeStart());
            dateEnd = new SimpleDateFormat("hh:mm").parse(teacherWorkingDaysDto.getTimeEnd());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert dateStart != null;
        TeacherWorkingDay teacherWorkingDay = new TeacherWorkingDay(
                dateStart.toInstant(),
                dateEnd.toInstant()
        );
        teacherWorkingDay.setExercise(exercisesRepository.findByName(teacherWorkingDaysDto.getExerciseName()).get());
        teacherWorkingDay.setWeekday(weekdaysRepository.findByName(teacherWorkingDaysDto.getWeekDayName()).get());
        return teacherWorkingDay;
    }

    public TeacherWorkingDayDto teacherWorkingDayToTeacherWorkingDayDto(TeacherWorkingDay teacherWorkingDay) {
        return new TeacherWorkingDayDto(
                teacherWorkingDay.getId(),
                teacherWorkingDay.getWeekday().getId(),
                teacherWorkingDay.getExercise().getId(),
                teacherWorkingDay.getWeekday().getName(),
                teacherWorkingDay.getExercise().getName(),
                teacherWorkingDay.getTime_start().toString(),
                teacherWorkingDay.getTime_end().toString()
        );
    }

    public LessonDto lessonToDto(Lesson lesson) {
        return new LessonDto(
                lesson.getId(),
                lesson.getName(),
                lesson.getLink(),
                lesson.getExercise().getId(),
                lesson.getExercise().getName(),
                lesson.getDtStart().toString()
        );
    }

    public Lesson lessonDtoToLesson(LessonDto lessonDto) {
        return new Lesson(
                lessonDto.getId(),
                lessonDto.getLesson(),
                lessonDto.getLink(),
                exercisesRepository.findById(lessonDto.getExerciseId()).get(),
                dateFormatter.stringToDateTime(lessonDto.getDtStart())
        );
    }

    public LessonsStudent lessonsStudentDtoToLessonsStudent(LessonsStudentsDto lessonsStudentsDto){
        return new LessonsStudent(
                lessonsStudentsDto.getId(),
                lessonRepository.findById(lessonsStudentsDto.getLessonId()).get(),
                userRepository.findById(lessonsStudentsDto.getStudentId()).get(),
                lessonsStudentsDto.getIsAttend(),
                lessonsStudentsDto.getIsAccepted(),
                lessonsStudentsDto.getDtCreate(),
                lessonsStudentsDto.getDtCreate()
        );
    }
    public LessonsStudentsDto LessonsStudentToLessonsStudentDto(LessonsStudent lessonsStudent){
        return new LessonsStudentsDto(lessonsStudent);
    }

}
