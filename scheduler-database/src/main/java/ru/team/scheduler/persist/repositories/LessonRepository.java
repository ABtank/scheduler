package ru.team.scheduler.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.team.scheduler.persist.entities.Lesson;

import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    Optional<Lesson> findById(Integer id);
}
