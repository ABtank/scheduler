package ru.team.scheduler.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.team.scheduler.persist.dto.DataToSendNotification;
import ru.team.scheduler.persist.entities.LessonsStudent;
import ru.team.scheduler.persist.entities.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface LessonsStudentsRepository extends JpaRepository<LessonsStudent, Integer> {

    List<LessonsStudent> findAllByStudent(User student);

    List<LessonsStudent> findAllByStudentId(Integer id);

    Optional<LessonsStudent> findById(Integer id);

    Optional<LessonsStudent> findByLessonIdAndStudentId(Integer lesson_id, Integer student_id);

    @Query(value = "SELECT\n" +
            "        users.email,\n" +
            "        lessons_students.id as lessons_students_id,\n" +
            "        lessons.dt_start as dt_start\n" +
            "    FROM exercises as exercises\n" +
            "        INNER JOIN lessons as lessons\n" +
            "        ON lessons.exercise_id = exercises.id\n" +
            "        INNER JOIN lessons_students as lessons_students\n" +
            "        ON lessons.id = lessons_students.lesson_id\n" +
            "        INNER JOIN users as users\n" +
            "        ON lessons_students.student_id = users.id\n" +
            "    WHERE\n" +
            "        exercises.is_validate\n" +
            "        AND NOT lessons_students.is_confirmation_request_sent\n" +
            "        AND TIMESTAMPDIFF(hour, :curDate, lessons.dt_start) <= 24", nativeQuery = true)
    List<DataToSendNotification> getStudentsToSendNotification(@Param("curDate") Date curDate);
}
