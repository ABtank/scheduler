package ru.team.scheduler.persist.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.team.scheduler.persist.entities.Discipline;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Integer>, JpaSpecificationExecutor<Discipline> {

    Optional<Discipline> findByName(String name);

    Optional<Discipline> findByNameLike(String namePattern);

    Set<Discipline> findByNameIn(Set<String> namePattern);
}