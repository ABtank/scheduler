package ru.team.scheduler.oapi.services;

import ru.team.scheduler.oapi.dto.ExerciseDto;
import ru.team.scheduler.persist.entities.Exercise;

import java.security.Principal;
import java.util.Optional;

public interface ExerciseService extends BasicService<Exercise> {
     Optional<Exercise> save(ExerciseDto o);
     Optional<Exercise> save(Principal principal, Exercise exercise);
}
