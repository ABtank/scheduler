package ru.team.sheduler.persist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.team.sheduler.persist.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    Optional<User> findByName(String name);

    List<User> findByNameLike(String namePattern);

    List<User> findByEmailLike(String emailPattern);

    List<User> findByEmailLikeAndNameLike(String emailPattern, String namePattern);

}