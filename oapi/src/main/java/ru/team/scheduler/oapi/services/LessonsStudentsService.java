package ru.team.scheduler.oapi.services;

import ru.team.scheduler.persist.entities.LessonsStudent;

import java.util.List;

public interface LessonsStudentsService extends BasicService<LessonsStudent, Integer> {
    List<LessonsStudent> findAllByStudentId(Integer id);
}
