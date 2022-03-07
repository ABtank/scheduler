package ru.team.scheduler.oapi.services;

import io.swagger.models.auth.In;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.internal.util.DateUtils;
import org.springframework.stereotype.Service;
import ru.team.scheduler.oapi.dto.*;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.oapi.utils.DateFormatterInstantToString;
import ru.team.scheduler.oapi.utils.DateFormatterStringToInstant;
import ru.team.scheduler.persist.entities.*;
import ru.team.scheduler.persist.repositories.CalendarDaysRepository;
import ru.team.scheduler.persist.repositories.DisciplineRepository;
import ru.team.scheduler.persist.repositories.TeacherWorkingDaysRepository;
import ru.team.scheduler.persist.repositories.WeekdaysRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeachersScheduleMasterService {
    private final LessonsStudentsService lessonsStudentsService;
    private final DisciplineService disciplineService;
    private final ExerciseService exerciseService;
    private final LessonService lessonService;
    private final WeekdaysRepository weekdaysRepository;
    private final TeacherWorkingDaysRepository teacherWorkingDaysRepository;
    private final CalendarDaysRepository calendarDaysRepository;
    private final MapperService mapperService;
    private final DateFormatterInstantToString dateFormatterInstantToString;
    private final DateFormatterStringToInstant dateFormatterStringToInstant;


    @Transactional
    public void creteNewTeachersSchedule(TeachersScheduleMasterRequestDto teachersScheduleMasterRequestDto,
                                         Principal principal) {
        Integer exerciseId = teachersScheduleMasterRequestDto.getExerciseId();
        Optional<Exercise> exerciseOptional = exerciseService.findById(exerciseId);
        if (exerciseOptional.isEmpty()) {
            throw new NotFoundException("Предмет, по которому отправлен запрос на формирование расписания не найден");
        }
        Exercise exercise = exerciseOptional.get();
        Instant today = Clock.systemDefaultZone().instant();
        Instant monthAfter = today.plus(30, ChronoUnit.DAYS);
        List<Weekday> weekdays = weekdaysRepository.findAll();
        List<TeacherWorkingDay> teacherWorkingDays = teacherWorkingDaysRepository.findAllByExerciseId(exerciseId);
        for (TeacherWorkingDay teacherWorkingDay : teacherWorkingDays) {
            List<CalendarDay> calendarDays = calendarDaysRepository.findByWeekdayId(
                    teacherWorkingDay.getWeekday().getId());
            for (CalendarDay calendarDay : calendarDays) {
                if (!calendarDay.getIsHoliday()) {
                    Instant timeStart = teacherWorkingDay.getTimeStart();
                    Date dateOfLesson = calendarDay.getDayDate();
                    Integer lessonsQuantity = teacherWorkingDay.getLessonsQuantity();
                    Instant lessonDateTimeStart = dateFormatterStringToInstant.stringToDateTime(
                            dateOfLesson.toString() + " " + dateFormatterInstantToString.timeFromInstant(
                                    timeStart));
                    for (int i = 0; i < lessonsQuantity; i++) {
                        Lesson lesson = new Lesson(exercise.getName(), "no link", exercise, lessonDateTimeStart);
                        lessonService.save(lesson, principal);
                        lessonDateTimeStart = lessonDateTimeStart
                                .plus(exercise.getDuration() + teacherWorkingDay.getBreakDuration(), ChronoUnit.MINUTES);
                    }
                }
            }
        }
    }
}
