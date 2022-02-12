package ru.team.scheduler.oapi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.dto.UserCreationDto;
import ru.team.scheduler.oapi.dto.UserDto;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.oapi.services.UserService;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1/users")
@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping(value = "/{id}")
    public UserDto findById(@PathVariable("id") Integer id) {
        return userService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public UserDto create(@RequestBody UserCreationDto userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getMatchingPassword())) {
            throw new IllegalArgumentException("password not matching");
        } else {
            userDTO.setId(null);
        }
        return userService.save(userDTO).orElseThrow(NotFoundException::new);
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDTO) {
        if (userDTO.getId() == null && userService.checkIsUnique(userDTO.getEmail(), userDTO.getId())) {
            throw new IllegalArgumentException("Id not found in the update request or email not unique");
        }
        return userService.update(userDTO).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        userService.deleteAll();
        return new ResponseEntity<>("-=You cannot delete all Users=-", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) {
        userService.deleteById(id);
        log.info("-=OK=-");
    }
}