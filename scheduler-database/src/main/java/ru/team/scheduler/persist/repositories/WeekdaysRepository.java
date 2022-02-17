package ru.team.scheduler.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.team.scheduler.persist.entities.Weekday;

import java.util.Optional;

@Repository
public interface WeekdaysRepository extends JpaRepository<Weekday, Integer> {
    Optional<Weekday> findById(Integer id);

    Optional<Weekday> findByName(String name);
}
