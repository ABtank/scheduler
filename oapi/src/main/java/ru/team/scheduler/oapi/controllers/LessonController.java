package ru.team.scheduler.oapi.controllers;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.constants.SwaggerConstant;
import ru.team.scheduler.oapi.dto.ExerciseDto;
import ru.team.scheduler.oapi.dto.lesson.LessonDto;
import ru.team.scheduler.oapi.dto.transfer.New;
import ru.team.scheduler.oapi.dto.transfer.Update;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.oapi.services.ExerciseService;
import ru.team.scheduler.oapi.services.LessonService;
import ru.team.scheduler.oapi.services.MapperService;
import ru.team.scheduler.persist.entities.Discipline;
import ru.team.scheduler.persist.entities.Exercise;
import ru.team.scheduler.persist.entities.Lesson;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/v1/lessons")
@Api(tags = {SwaggerConstant.API_LESSON})
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;
    private final ExerciseService exerciseService;
    private final MapperService mapperService;

    @GetMapping
    public List<LessonDto> getAllLessons() {
        return lessonService.findAll().stream().map(mapperService::lessonToDto).toList();
    }

    @GetMapping("/{id}")
    public LessonDto findById(@PathVariable Integer id){
        return lessonService.findById(id).map(mapperService::lessonToDto).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LessonDto create(@ApiIgnore Principal principal, @Validated(New.class) @RequestBody LessonDto lessonDto) {
        Lesson newLesson  = mapperService.lessonDtoToLesson(lessonDto);
        Optional<Exercise> exerciseOptional = exerciseService.findById(lessonDto.getExerciseId());
        if(exerciseOptional.isPresent()){
            Exercise exercise = exerciseOptional.get();
            Discipline discipline = exercise.getDiscipline();
            newLesson.setExercise(exercise);
        }
        else throw new NotFoundException("Урока с данным Id не найдено");
        return lessonService.save(newLesson, principal).map(mapperService::lessonToDto).orElseThrow(NotFoundException::new);
    }

    @PutMapping
    public LessonDto updateLesson(@ApiIgnore Principal principal, @Validated(Update.class) @RequestBody LessonDto lessonDto) {
        if (lessonDto.getId() == null) {
            throw new IllegalArgumentException("Id not found in the update request");
        }
        Lesson lesson  = mapperService.lessonDtoToLesson(lessonDto);
        Optional<Exercise> exerciseOptional = exerciseService.findById(lessonDto.getExerciseId());
        if(exerciseOptional.isPresent()){
            Exercise exercise = exerciseOptional.get();
            Discipline discipline = exercise.getDiscipline();
            lesson.setExercise(exercise);
        }
        Lesson updatedLesson = lessonService.update(lesson, principal).orElseThrow(NotFoundException::new);
        return mapperService.lessonToDto(updatedLesson);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        return new ResponseEntity<>("-=You cannot delete all lessons=-", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id,@ApiIgnore Principal principal) {
        lessonService.deleteById(id, principal);
        log.info("-=OK=-");
    }
}
