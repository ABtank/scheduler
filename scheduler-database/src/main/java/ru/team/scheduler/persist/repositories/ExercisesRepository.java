package ru.team.scheduler.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.team.scheduler.persist.entities.Exercise;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

@Repository
public interface ExercisesRepository extends JpaRepository<Exercise, Integer> {
    Optional<Exercise> findById(Integer id);

    Optional<Exercise> findByName(String name);
}
