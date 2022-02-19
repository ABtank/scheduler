package ru.team.scheduler.oapi.controllers;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.constants.SwaggerConstant;
import ru.team.scheduler.oapi.dto.ExerciseDto;
import ru.team.scheduler.oapi.dto.transfer.New;
import ru.team.scheduler.oapi.dto.transfer.Update;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.oapi.services.ExerciseService;
import ru.team.scheduler.oapi.services.MapperService;
import ru.team.scheduler.persist.entities.Discipline;
import ru.team.scheduler.persist.entities.Exercise;
import ru.team.scheduler.persist.entities.User;

import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/exercises")
@Api(tags = {SwaggerConstant.API_EXERCISE})
@RequiredArgsConstructor
public class ExercisesController {
    private final ExerciseService exerciseService;
    private final MapperService mapperService;

    @GetMapping
    public List<ExerciseDto> getAllExercises() {
        return exerciseService.findAll().stream().map(mapperService::exerciseToDto).toList();
    }

    @GetMapping("/{id}")
    public ExerciseDto findById(@PathVariable Integer id){
        return exerciseService.findById(id).map(mapperService::exerciseToDto).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseDto create(Principal principal, @Validated(New.class) @RequestBody ExerciseDto exerciseDto) {
        Exercise newExercise = mapperService.exerciseDTOtoExercise(exerciseDto);
        newExercise.setDiscipline(new Discipline(exerciseDto.getDiscipline()));
        return exerciseService.save(principal, newExercise).map(mapperService::exerciseToDto).orElseThrow(NotFoundException::new);
    }

    @PutMapping
    public ExerciseDto updateExercise(Principal principal, @Validated(Update.class) @RequestBody ExerciseDto exerciseDto) {
        if (exerciseDto.getId() == null) {
            throw new IllegalArgumentException("Id not found in the update request");
        }
        Exercise exercise = mapperService.exerciseDTOtoExercise(exerciseDto);
        exercise.setDiscipline(new Discipline(exerciseDto.getDiscipline()));
        Exercise updatedExercise = exerciseService.update(principal, exercise).orElseThrow(NotFoundException::new);
        return mapperService.exerciseToDto(updatedExercise);
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
