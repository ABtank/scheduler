package ru.team.scheduler.oapi.services;

import ru.team.scheduler.oapi.dto.DisciplineDto;
import ru.team.scheduler.persist.entities.Discipline;

import java.util.Optional;

public interface DisciplineService extends BasicService<DisciplineDto> {
    Optional<DisciplineDto> findByName(String name);
    Optional<Discipline> findEntityByName(String name);
}
