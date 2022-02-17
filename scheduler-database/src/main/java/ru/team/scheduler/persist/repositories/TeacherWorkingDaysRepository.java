package ru.team.scheduler.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.team.scheduler.persist.entities.TeacherWorkingDay;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherWorkingDaysRepository extends JpaRepository<TeacherWorkingDay, Integer> {
    List<TeacherWorkingDay> findByExerciseId(Integer id);

    Optional<TeacherWorkingDay> findById(Integer id);
}
