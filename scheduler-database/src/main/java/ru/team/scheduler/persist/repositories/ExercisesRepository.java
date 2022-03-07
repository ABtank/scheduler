package ru.team.scheduler.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.team.scheduler.persist.dto.ExerciseLessonDto;
import ru.team.scheduler.persist.entities.Exercise;
import ru.team.scheduler.persist.entities.User;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExercisesRepository extends JpaRepository<Exercise, Integer> {
    Optional<Exercise> findById(Integer id);

    @Query(value = "SELECT\n" +
            "      exercises.id as exerciseId,\n" +
            "      exercises.duration as exerciseDuration,\n" +
            "      lessons.id as lessonId,\n" +
            "      lessons.name as lessonName,\n" +
            "      lessons.dt_start as lessonsDtstart,\n" +
            "      disciplines.name as disciplineName,\n" +
            "      CONCAT(teachers.last_name, ' ', teachers.first_name, ' ', teachers.middle_name) as teacherFullName,\n" +
            "      students.email as studentEmail\n" +
            "FROM exercises as exercises\n" +
            "    INNER JOIN lessons as lessons\n" +
            "    ON lessons.exercise_id = exercises.id\n" +
            "    INNER JOIN lessons_students as lessons_students\n" +
            "    ON lessons.id = lessons_students.lesson_id\n" +
            "    INNER JOIN users as students\n" +
            "    ON lessons_students.student_id = students.id\n" +
            "    INNER JOIN users as teachers\n" +
            "    ON exercises.teacher_id = teachers.id\n" +
            "    INNER JOIN disciplines as disciplines\n" +
            "    ON exercises.discipline_id = disciplines.id\n" +
            "WHERE\n" +
            "    (lessons.dt_modify  >= (NOW () - INTERVAL '73 MINUTE')\n" +
            "    AND\n" +
            "    lessons.dt_modify  <= (NOW () - INTERVAL '60 MINUTE') )\n" +
            "    OR\n" +
            "    (exercises.dt_modify  >= (NOW () - INTERVAL '73 MINUTE')\n" +
            "    AND\n" +
            "    exercises.dt_modify  <= (NOW () - INTERVAL '60 MINUTE') )", nativeQuery = true)
    List<ExerciseLessonDto> getLastExercisesChanges();

    Optional<Exercise> findByName(String name);
}
