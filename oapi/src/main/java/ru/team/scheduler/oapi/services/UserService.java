package ru.team.scheduler.oapi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import ru.team.scheduler.oapi.dto.UserCreationDto;
import ru.team.scheduler.persist.entities.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService extends BasicService<User, Integer>{
    Optional<User> findByEmail(String email);
    Optional<User> registration(UserCreationDto userCreationDTO);
    Page<User> findAll(Map<String, String> params, PageRequest pageRequest);
    List<User> findAll(Specification<User> spec);
    boolean checkIsUnique(String email, Integer id);
}

