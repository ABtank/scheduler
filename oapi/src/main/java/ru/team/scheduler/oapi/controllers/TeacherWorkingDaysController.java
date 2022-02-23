package ru.team.scheduler.oapi.controllers;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.constants.SwaggerConstant;
import ru.team.scheduler.oapi.dto.TeacherWorkingDayDto;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.oapi.services.TeacherWorkingDaysService;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.List;

@Slf4j
@RequestMapping("/api/v1/teacherWorkingDays")
@RestController
@Api(tags = {SwaggerConstant.API_USER})
@RequiredArgsConstructor
public class TeacherWorkingDaysController {
   private final TeacherWorkingDaysService teacherWorkingDaysService;

    @GetMapping
    public List<TeacherWorkingDayDto> getAllTeacherWorkingDays() {
        return teacherWorkingDaysService.findAll();
    }

    @GetMapping(value = "/{id}")
    public TeacherWorkingDayDto findById(@PathVariable("id") Integer id) {
        return teacherWorkingDaysService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherWorkingDayDto create(@RequestBody TeacherWorkingDayDto teacherWorkingDayDto, @ApiIgnore Principal principal) {
        return teacherWorkingDaysService.save(teacherWorkingDayDto, principal).orElseThrow(NotFoundException::new);
    }

    @PutMapping
    public TeacherWorkingDayDto updateTeacherWorkingDay(@RequestBody TeacherWorkingDayDto teacherWorkingDayDto, @ApiIgnore Principal principal) {
        return teacherWorkingDaysService.save(teacherWorkingDayDto, principal).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        return new ResponseEntity<>("-=You cannot delete all teacherWorkingDays=-", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id, @ApiIgnore Principal principal) {
        teacherWorkingDaysService.deleteById(id, principal);
        log.info("-=OK=-");
    }
}

