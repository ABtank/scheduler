package ru.team.scheduler.oapi.services;

import ru.team.scheduler.persist.entities.Exercise;

import java.security.Principal;
import java.util.Optional;

public interface ExerciseService extends BasicService<Exercise,Integer> {
     Optional<Exercise> update(Exercise exercise, Principal principal);
}
