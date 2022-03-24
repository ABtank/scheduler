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
import ru.team.scheduler.oapi.services.LessonsStudentsServiceImpl;
import ru.team.scheduler.oapi.services.MapperService;
import ru.team.scheduler.persist.entities.LessonsStudent;
import springfox.documentation.annotations.ApiIgnore;
import java.security.Principal;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequestMapping("/api/v1/lessonsStudents")
@RestController
@Api(tags = {SwaggerConstant.API_EXERCISE})
@RequiredArgsConstructor
public class LessonsStudentsController {
    private final LessonsStudentsServiceImpl lessonsStudentsService;
    private final MapperService mapperService;

    @GetMapping(value = "/{id}")
    public List<LessonsStudentsDto> findById(@PathVariable("id") Integer id) {
        List<LessonsStudent> ls = lessonsStudentsService.findAllByStudentId(id);
        return ls.stream().map(LessonsStudentsDto::new).collect(toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LessonsStudentsDto create(@RequestBody LessonsStudentsDto lessonsStudentsDto, @ApiIgnore Principal principal) {
        LessonsStudent lessonsStudent = mapperService.lessonsStudentDtoToLessonsStudent(lessonsStudentsDto);
        LessonsStudent ls = lessonsStudentsService.save(lessonsStudent, principal).orElseThrow(NotFoundException::new);
        return mapperService.LessonsStudentToLessonsStudentDto(ls);
    }

    @PutMapping
    public LessonsStudentsDto updateLessonsStudentsDto(@RequestBody LessonsStudentsDto lessonsStudentsDto, @ApiIgnore Principal principal) {
        if (lessonsStudentsDto.getId() == null) {
            throw new IllegalArgumentException("Id not found in the update request");
        }
        LessonsStudent lessonsStudent = mapperService.lessonsStudentDtoToLessonsStudent(lessonsStudentsDto);
        LessonsStudent ls = lessonsStudentsService.save(lessonsStudent, principal).orElseThrow(NotFoundException::new);
        return mapperService.LessonsStudentToLessonsStudentDto(ls);
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
