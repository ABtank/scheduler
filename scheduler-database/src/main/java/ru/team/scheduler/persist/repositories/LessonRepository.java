package ru.team.scheduler.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.team.scheduler.persist.dto.LessonByIdDto;
import ru.team.scheduler.persist.entities.Lesson;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    Optional<Lesson> findById(Integer id);

    @Query(value = "SELECT\n" +
            "    lessons.id as lessons_id,\n" +
            "    lessons.name as lesson_name,\n" +
            "    lessons.dt_start as time_start,\n" +
            "    exercises.duration as duration,\n" +
            "    users.first_name as teacher_first_name,\n" +
            "    users.middle_name as teacher_middle_name,\n" +
            "    users.last_name as teacher_last_name,\n" +
            "    disciplines.name as discipline_name\n" +
            "FROM lessons as lessons\n" +
            "    INNER JOIN exercises as exercises\n" +
            "    ON lessons.exercise_id = exercises.id\n" +
            "    INNER JOIN users as users\n" +
            "    ON exercises.teacher_id = users.id\n" +
            "    INNER JOIN disciplines as disciplines\n" +
            "    ON exercises.discipline_id = disciplines.id\n" +
            "WHERE\n" +
            "    lessons.id = :lessons_id", nativeQuery = true)

    Optional<LessonByIdDto> getLessonById(@Param("lessons_id") Integer lessons_id);

    List<Lesson> findAll();

    Optional<Lesson> findByExerciseId(Integer exerciseId);
}
