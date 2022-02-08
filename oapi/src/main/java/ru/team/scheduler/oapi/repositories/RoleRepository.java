package ru.team.scheduler.oapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.team.scheduler.oapi.models.entities.Role;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {

    Optional<Role> findByName(String name);

    Optional<Role> findByNameLike(String namePattern);

    Set<Role> findByNameIn(Set<String> roles);
}