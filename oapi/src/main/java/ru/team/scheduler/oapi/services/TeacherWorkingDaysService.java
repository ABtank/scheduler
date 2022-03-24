package ru.team.scheduler.oapi.services;

import ru.team.scheduler.oapi.dto.TeacherWorkingDayDto;
import ru.team.scheduler.persist.entities.TeacherWorkingDay;

import java.util.Optional;

public interface TeacherWorkingDaysService extends BasicService<TeacherWorkingDay,Integer>{
    Optional<TeacherWorkingDay> update(TeacherWorkingDay teacherWorkingDay);
}
