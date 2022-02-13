package ru.team.scheduler.oapi.services;

import ru.team.scheduler.oapi.dto.ExerciseDto;

import java.security.Principal;
import java.util.Optional;

public interface ExerciseService extends BasicService<ExerciseDto> {
     Optional<ExerciseDto> save(Principal principal, ExerciseDto exerciseDto);
}
