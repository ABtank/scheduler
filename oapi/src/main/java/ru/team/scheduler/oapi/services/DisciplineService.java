package ru.team.scheduler.oapi.services;

import ru.team.scheduler.oapi.dto.DisciplineDto;
import ru.team.scheduler.persist.entities.Discipline;

import java.util.List;
import java.util.Optional;

public interface DisciplineService extends BasicService<DisciplineDto> {
    List<DisciplineDto> findAll(String name);
    Optional<DisciplineDto> findByName(String name);
    Optional<Discipline> findEntityByName(String name);
}
