package ru.team.scheduler.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.team.scheduler.persist.dto.StudentScheduleDto;
import ru.team.scheduler.persist.entities.User;

import java.util.List;

public interface StudentRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT\n" +
            "    ls.id as lessons_students_id,\n" +
            "    lessons.name as lesson_name,\n" +
            "    lessons.dt_start as time_start,\n" +
            "    exercises.name as exercise_name,\n" +
            "    exercises.duration as duration,\n" +
            "    users.first_name as teacher_first_name,\n" +
            "    users.middle_name as teacher_middle_name,\n" +
            "    users.last_name as teacher_last_name,\n" +
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
    List<StudentScheduleDto> getUserSchedule(@Param("user_id") Integer userId);

}
