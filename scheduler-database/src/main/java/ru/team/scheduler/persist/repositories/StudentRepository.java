package ru.team.scheduler.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.team.scheduler.persist.dto.LessonByIdDto;
import ru.team.scheduler.persist.dto.StudentScheduleDto;
import ru.team.scheduler.persist.entities.User;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT\n" +
            "    lessons.name as lesson_name,\n" +
            "    ls.id as lessons_students_id,\n" +
            "    lessons.dt_start as time_start,\n" +
            "    exercises.name as exercise_name,\n" +
            "    exercises.duration as duration,\n" +
            "    users.name as teacher_name,\n" +
            "    disciplines.name as discipline_name\n" +
            "FROM lessons_students as ls\n" +
            "    INNER JOIN lessons as lessons\n" +
            "    ON ls.lesson_id = lessons.id\n" +
            "    INNER JOIN exercises as exercises\n" +
            "    ON lessons.exercise_id = exercises.id\n" +
            "    INNER JOIN users as users\n" +
            "    ON exercises.teacher_id = users.id\n" +
            "    INNER JOIN disciplines as disciplines\n" +
            "    ON exercises.discipline_id = disciplines.id\n" +
            "WHERE\n" +
            "    ls.student_id = :user_id", nativeQuery = true)
    List<StudentScheduleDto> getUserSchedule(@Param("user_id") Integer user_id);

    @Query(value = "SELECT\n" +
            "    lessons.id as lessons_id,\n" +
            "    lessons.name as lessons_name,\n" +
            "    lessons.dt_start as time_start,\n" +
            "    exercises.duration as duration,\n" +
            "    users.name as teacher_name,\n" +
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

}
