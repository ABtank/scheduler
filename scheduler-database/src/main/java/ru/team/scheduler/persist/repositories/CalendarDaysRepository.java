package ru.team.scheduler.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.team.scheduler.persist.entities.CalendarDay;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarDaysRepository extends JpaRepository<CalendarDay, Integer> {
    Optional<CalendarDay> findById(Integer id);

    Optional<CalendarDay> findByDayDate(Instant dayDate);

    List<CalendarDay> findByWeekdayId(Integer id);

}
