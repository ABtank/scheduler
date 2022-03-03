package ru.team.scheduler.oapi.controllers;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.constants.SwaggerConstant;
import ru.team.scheduler.oapi.dto.TeacherWorkingDayDto;
import ru.team.scheduler.oapi.dto.transfer.New;
import ru.team.scheduler.oapi.dto.transfer.Update;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.oapi.services.MapperService;
import ru.team.scheduler.oapi.services.TeacherWorkingDaysService;
import ru.team.scheduler.persist.entities.TeacherWorkingDay;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.List;

@Slf4j
@RequestMapping("/api/v1/teacherWorkingDays")
@RestController
@Api(tags = {SwaggerConstant.API_TEACHER_WORKING_DAY})
@RequiredArgsConstructor
public class TeacherWorkingDaysController {
    private final TeacherWorkingDaysService teacherWorkingDaysService;
    private final MapperService mapperService;

    @GetMapping
    public List<TeacherWorkingDayDto> getAllTeacherWorkingDays() {
        return teacherWorkingDaysService.findAll().stream()
                .map(mapperService::teacherWorkingDayToTeacherWorkingDayDto).toList();
    }

    @GetMapping(value = "/{id}")
    public TeacherWorkingDayDto findById(@PathVariable("id") Integer id) {
        return teacherWorkingDaysService.findById(id).map(mapperService::teacherWorkingDayToTeacherWorkingDayDto)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherWorkingDayDto create
            (@Validated(New.class) @RequestBody TeacherWorkingDayDto teacherWorkingDayDto, @ApiIgnore Principal principal) {
        TeacherWorkingDay teacherWorkingDay = mapperService.teacherWorkingDayDtoToTeacherWorkingDay(teacherWorkingDayDto);
        return teacherWorkingDaysService.save(teacherWorkingDay, principal).map(mapperService::teacherWorkingDayToTeacherWorkingDayDto)
                .orElseThrow(NotFoundException::new);
    }

    @PutMapping
    public TeacherWorkingDayDto updateTeacherWorkingDay
            (@Validated(Update.class) @RequestBody TeacherWorkingDayDto teacherWorkingDayDto) {
        if (teacherWorkingDayDto.getId() == null) {
            throw new IllegalArgumentException("Id not found in the update request");
        }
        TeacherWorkingDay teacherWorkingDay = mapperService.teacherWorkingDayDtoToTeacherWorkingDay(teacherWorkingDayDto);
        TeacherWorkingDay updatedTeacherWorkingDay = teacherWorkingDaysService
                .update(teacherWorkingDay).orElseThrow(NotFoundException::new);
        return mapperService.teacherWorkingDayToTeacherWorkingDayDto(updatedTeacherWorkingDay);
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

