package ru.team.scheduler.oapi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import ru.team.scheduler.oapi.dto.UserCreationDto;
import ru.team.scheduler.oapi.dto.UserDto;
import ru.team.scheduler.persist.entities.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    List<UserDto> findAll();
    Optional<UserDto> findById(Integer id);
    Optional<UserDto> findByEmail(String email);
    void deleteById(Integer id);
    Optional<UserDto> save(UserCreationDto userCreationDTO);
    Optional<UserDto> update(UserDto userDTO);
    void deleteAll();

    Page<UserDto> findAll(Map<String, String> params, PageRequest pageRequest);

    List<UserDto> findAll(Specification<User> spec);
    boolean checkIsUnique(String email, Integer id);
    long count();
}

