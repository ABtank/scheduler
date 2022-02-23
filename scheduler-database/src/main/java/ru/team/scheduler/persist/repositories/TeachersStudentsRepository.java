package ru.team.scheduler.persist.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.team.scheduler.persist.entities.TeachersStudent;
import ru.team.scheduler.persist.entities.User;

@Repository
public interface TeachersStudentsRepository extends JpaRepository<TeachersStudent, Integer> {
    Optional<TeachersStudent> findByStudentAndTeacher(User student, User teacher);
}
