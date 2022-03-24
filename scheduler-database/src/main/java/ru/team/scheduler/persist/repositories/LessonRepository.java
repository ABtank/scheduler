package ru.team.scheduler.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.team.scheduler.persist.responsesOfDataBase.LessonByIdResponse;
import ru.team.scheduler.persist.entities.Lesson;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    Optional<Lesson> findById(Integer id);

    @Query(value = "SELECT\n" +
            "    lessons.id as lessonsId,\n" +
            "    lessons.name as lessonName,\n" +
            "    lessons.dt_start as timeStart,\n" +
            "    exercises.duration as duration,\n" +
            "    users.first_name as teacherFirstName,\n" +
            "    users.middle_name as teacherMiddleName,\n" +
            "    users.last_name as teacherLastName,\n" +
            "    disciplines.name as disciplineName\n" +
            "FROM lessons as lessons\n" +
            "    INNER JOIN exercises as exercises\n" +
            "    ON lessons.exercise_id = exercises.id\n" +
            "    INNER JOIN users as users\n" +
            "    ON exercises.teacher_id = users.id\n" +
            "    INNER JOIN disciplines as disciplines\n" +
            "    ON exercises.discipline_id = disciplines.id\n" +
            "WHERE\n" +
            "    lessons.id = :lessons_id", nativeQuery = true)
    Optional<LessonByIdResponse> getLessonById(@Param("lessons_id") Integer lessonsId);

    List<Lesson> findAll();

    Optional<Lesson> findByExerciseId(Integer exerciseId);
}
