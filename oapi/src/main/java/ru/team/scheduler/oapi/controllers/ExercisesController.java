package ru.team.scheduler.oapi.controllers;

import io.swagger.annotations.Api;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.constants.SwaggerConstant;
import ru.team.scheduler.oapi.dto.DisciplineDto;
import ru.team.scheduler.oapi.dto.ExerciseDto;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.oapi.services.ExerciseService;

import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/exercises")
@Api(tags = {SwaggerConstant.API_EXERCISE})
@RequiredArgsConstructor
public class ExercisesController {
    private final ExerciseService exerciseService;

    @GetMapping
    public List<ExerciseDto> getAllExercises() {
        return exerciseService.findAll();
    }

    @GetMapping("/{id}")
    public ExerciseDto findById(@PathVariable Integer id){
        return exerciseService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseDto create(Principal principal,  @RequestBody ExerciseDto exerciseDto) {
        return exerciseService.save(principal, exerciseDto).orElseThrow(NotFoundException::new);
    }

    @PutMapping
    public ExerciseDto updateDiscipline(@RequestBody ExerciseDto exerciseDto) {
        if (exerciseDto.getId() == null) {
            throw new IllegalArgumentException("Id not found in the update request");
        }
        return exerciseService.save(exerciseDto).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        exerciseService.deleteAll();
        return new ResponseEntity<>("-=You cannot delete all exercises=-", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) {
        exerciseService.deleteById(id);
        log.info("-=OK=-");
    }
}
