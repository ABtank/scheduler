package ru.team.scheduler.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.team.scheduler.persist.entities.LessonsStudent;
import ru.team.scheduler.persist.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonsStudentsRepository extends JpaRepository<LessonsStudent, Integer> {

    List<LessonsStudent> findAllByStudent(User student);

    List<LessonsStudent> findAllByStudentId(Integer id);

    Optional<LessonsStudent> findById(Integer id);

}
