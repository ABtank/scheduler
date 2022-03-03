package ru.team.scheduler.oapi.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.team.scheduler.oapi.dto.ExerciseDto;
import ru.team.scheduler.oapi.dto.LessonsStudentsDto;
import ru.team.scheduler.oapi.dto.TeacherWorkingDayDto;
import ru.team.scheduler.oapi.dto.lesson.LessonDto;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.oapi.utils.DateFormatterInstantToString;
import ru.team.scheduler.oapi.utils.DateFormatterStringToInstant;
import ru.team.scheduler.persist.entities.Exercise;
import ru.team.scheduler.persist.entities.Lesson;
import ru.team.scheduler.persist.entities.LessonsStudent;
import ru.team.scheduler.persist.entities.TeacherWorkingDay;
import ru.team.scheduler.persist.entities.Weekday;
import ru.team.scheduler.persist.repositories.ExercisesRepository;
import ru.team.scheduler.persist.repositories.WeekdaysRepository;
import ru.team.scheduler.persist.repositories.*;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class MapperService {
    private final WeekdaysRepository weekdaysRepository;
    private final ExercisesRepository exercisesRepository;
    private final DateFormatterStringToInstant dateFormatterStringToInstant;
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

    @Transactional
    public TeacherWorkingDay teacherWorkingDayDtoToTeacherWorkingDay(TeacherWorkingDayDto teacherWorkingDaysDto) {
        TeacherWorkingDay teacherWorkingDay = new TeacherWorkingDay(
                dateFormatterStringToInstant.stringToTime(teacherWorkingDaysDto.getTimeStart()),
                dateFormatterStringToInstant.stringToTime(teacherWorkingDaysDto.getTimeEnd())
        );
        Optional<Exercise> exercise = exercisesRepository.findById(teacherWorkingDaysDto.getExerciseId());
        if(exercise.isEmpty()){
            throw new NotFoundException("Exercise not found");
        }
        else{
            teacherWorkingDay.setExercise(exercise.get());
        }
        Optional<Weekday> weekday = weekdaysRepository.findById(teacherWorkingDaysDto.getWeekdayId());
        if(weekday.isEmpty()){
            throw new NotFoundException("Weekday with such name is not found");
        }
        else {
            teacherWorkingDay.setWeekday(weekday.get());
        }
        return teacherWorkingDay;
    }

    public TeacherWorkingDayDto teacherWorkingDayToTeacherWorkingDayDto(TeacherWorkingDay teacherWorkingDay) {
        return new TeacherWorkingDayDto(
                teacherWorkingDay.getId(),
                teacherWorkingDay.getWeekday().getId(),
                teacherWorkingDay.getExercise().getId(),
                teacherWorkingDay.getWeekday().getName(),
                teacherWorkingDay.getExercise().getName(),
                teacherWorkingDay.getTimeStart().toString(),
                teacherWorkingDay.getTimeEnd().toString()
        );
    }

    public LessonDto lessonToDto(Lesson lesson) {
        return new LessonDto(
                lesson.getId(),
                lesson.getName(),
                lesson.getLink(),
                lesson.getExercise().getId(),
                lesson.getExercise().getName(),
                lesson.getDtStart().toString().replaceAll("[TZ]", " ")
        );
    }

    public Lesson lessonDtoToLesson(LessonDto lessonDto) {
        return new Lesson(
                lessonDto.getId(),
                lessonDto.getLesson(),
                lessonDto.getLink(),
                exercisesRepository.findById(lessonDto.getExerciseId()).get(),
                dateFormatterStringToInstant.stringToDateTime(lessonDto.getDtStart())
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
