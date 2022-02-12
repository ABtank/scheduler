package ru.team.scheduler.oapi.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.team.scheduler.oapi.dto.*;
import ru.team.scheduler.persist.entities.Discipline;
import ru.team.scheduler.persist.entities.TeacherWorkingDay;
import ru.team.scheduler.persist.repositories.DisciplineRepository;
import ru.team.scheduler.persist.repositories.TeacherWorkingDaysRepository;
import ru.team.scheduler.persist.repositories.WeekdaysRepository;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Data
@RequiredArgsConstructor
public class TeachersScheduleMasterService {
    private final LessonsStudentsService lessonsStudentsService;
    private final DisciplineService disciplineService;
    private final ExerciseService exerciseService;
    private final DisciplineRepository disciplineRepository;
    private final WeekdaysRepository weekdaysRepository;
    private final TeacherWorkingDaysRepository teacherWorkingDaysRepository;
    private final MapperService mapperService;


    @Transactional
    public void creteNewTeachersSchedule
            (TeachersScheduleMasterRequestDto teachersScheduleMasterRequestDto) {
        if (disciplineService.findByName(teachersScheduleMasterRequestDto.getExerciseDto().getDisciplineTitle()).isEmpty())
        {
            disciplineService.save(new DisciplineDto(teachersScheduleMasterRequestDto.getExerciseDto().getDisciplineTitle()));
        }
        Discipline discipline = disciplineRepository.findByName(teachersScheduleMasterRequestDto.getExerciseDto().getDisciplineTitle()).get();
        teachersScheduleMasterRequestDto.getExerciseDto().setDisciplineId(discipline.getId());
        ExerciseDto exerciseDto = exerciseService.save(teachersScheduleMasterRequestDto.getExerciseDto()).get();
        List<TeacherWorkingDayDto> teacherWorkingDayDtos = teachersScheduleMasterRequestDto.getTeacherWorkingDaysDtos();
        teacherWorkingDayDtos.forEach(o->o.setExerciseId(exerciseDto.getId()));
        teacherWorkingDayDtos.forEach(o->o.setWeekdayId(weekdaysRepository.findByName(o.getWeekDayName()).get().getId()));

            List<TeacherWorkingDay> teacherWorkingDays = teacherWorkingDayDtos.stream()
                    .map(mapperService::teacherWorkingDayDtoToTeacherWorkingDay).toList();
        teacherWorkingDaysRepository.saveAll(teacherWorkingDays);

    }
}
