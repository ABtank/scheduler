package ru.team.scheduler.oapi.services;

import ru.team.scheduler.persist.entities.Discipline;

import java.util.List;
import java.util.Optional;

public interface DisciplineService extends BasicService<Discipline> {
    List<Discipline> findAll(String name);
    Optional<Discipline> findByName(String name);
}
