package ru.team.scheduler.oapi.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.dto.DisciplineDto;
import ru.team.scheduler.oapi.dto.TeacherWorkingDayDto;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.oapi.services.TeacherWorkingDaysService;
import ru.team.scheduler.persist.entities.TeacherWorkingDay;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1/teacherWorkingDays")
@RestController
@RequiredArgsConstructor
public class TeacherWorkingDaysController {
   private final TeacherWorkingDaysService teacherWorkingDaysService;

    @GetMapping
    public List<TeacherWorkingDayDto> getAllDisciplines() {
        return teacherWorkingDaysService.findAll();
    }

    @GetMapping(value = "/{id}")
    public TeacherWorkingDayDto findById(@PathVariable("id") Integer id) {
        return teacherWorkingDaysService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherWorkingDayDto create(@RequestBody TeacherWorkingDayDto teacherWorkingDayDto) {
        return teacherWorkingDaysService.save(teacherWorkingDayDto).orElseThrow(NotFoundException::new);
    }

    @PutMapping
    public TeacherWorkingDayDto updateTeacherWorkingDay(@RequestBody TeacherWorkingDayDto teacherWorkingDayDto) {
        return teacherWorkingDaysService.save(teacherWorkingDayDto).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        teacherWorkingDaysService.deleteAll();
        return new ResponseEntity<>("-=You cannot delete all teacherWorkingDays=-", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) {
        teacherWorkingDaysService.deleteById(id);
        log.info("-=OK=-");
    }
}
