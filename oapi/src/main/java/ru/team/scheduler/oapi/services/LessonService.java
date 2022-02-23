package ru.team.scheduler.oapi.services;

import ru.team.scheduler.persist.entities.Exercise;
import ru.team.scheduler.persist.entities.Lesson;

import java.security.Principal;
import java.util.Optional;

public interface LessonService extends BasicService<Lesson, Integer> {
    Optional<Lesson> update(Lesson lesson, Principal principal);
}
