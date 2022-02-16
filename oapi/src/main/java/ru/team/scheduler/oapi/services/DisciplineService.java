package ru.team.scheduler.oapi.services;

import ru.team.scheduler.oapi.dto.DisciplineDto;

import java.util.List;
import java.util.Map;

public interface DisciplineService extends BasicService<DisciplineDto> {
    List<DisciplineDto> findAll(String name);
}
