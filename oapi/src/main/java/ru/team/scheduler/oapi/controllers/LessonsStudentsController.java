package ru.team.scheduler.oapi.controllers;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.constants.SwaggerConstant;
import ru.team.scheduler.oapi.dto.LessonsStudentsDto;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.oapi.services.LessonsStudentsService;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

@Slf4j
@RequestMapping("/api/v1/lessonsStudents")
@RestController
@Api(tags = {SwaggerConstant.API_EXERCISE})
@RequiredArgsConstructor
public class LessonsStudentsController {
    private final LessonsStudentsService lessonsStudentsService;

    @GetMapping(value = "/{id}")
    public LessonsStudentsDto findById(@PathVariable("id") Integer id) {
        return lessonsStudentsService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LessonsStudentsDto create(@RequestBody LessonsStudentsDto lessonsStudentsDto, @ApiIgnore Principal principal) {
        return lessonsStudentsService.save(lessonsStudentsDto, principal).orElseThrow(NotFoundException::new);
    }

    @PutMapping
    public LessonsStudentsDto updateLessonsStudentsDto(@RequestBody LessonsStudentsDto lessonsStudentsDto, @ApiIgnore Principal principal) {
        if (lessonsStudentsDto.getId() == null) {
            throw new IllegalArgumentException("Id not found in the update request");
        }
        return lessonsStudentsService.save(lessonsStudentsDto, principal).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        return new ResponseEntity<>("-=You cannot delete all disciplines=-", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id, @ApiIgnore Principal principal) {
        lessonsStudentsService.deleteById(id, principal);
        log.info("-=OK=-");
    }
}
