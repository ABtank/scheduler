package ru.team.scheduler.oapi.controllers;

import io.swagger.annotations.Api;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.constants.SwaggerConstant;
import ru.team.scheduler.oapi.dto.ExerciseDto;
import ru.team.scheduler.oapi.dto.TeacherWorkingDayDto;
import ru.team.scheduler.oapi.dto.TeachersScheduleMasterRequestDto;
import ru.team.scheduler.oapi.dto.TeachersScheduleMasterResponseDto;
import ru.team.scheduler.oapi.services.ExerciseService;
import ru.team.scheduler.oapi.services.MapperService;
import ru.team.scheduler.oapi.services.TeachersScheduleMasterService;
import ru.team.scheduler.persist.repositories.TeacherWorkingDaysRepository;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/teachersScheduleMaster")
@Api(tags = {SwaggerConstant.API_TEACHER_SCHEDULE_MASTER})
@RequiredArgsConstructor
public class TeachersScheduleMasterController {
    private final TeachersScheduleMasterService teachersScheduleMasterService;

    @PostMapping
    public void createNewTeacherSchedule
            (@RequestBody TeachersScheduleMasterRequestDto teachersScheduleMasterRequestDto, Principal principal){
            teachersScheduleMasterService.creteNewTeachersSchedule(teachersScheduleMasterRequestDto, principal);
    }

//    @GetMapping
//    public TeachersScheduleMasterRequestDto  test(){
//        List<TeacherWorkingDayDto> teacherWorkingDayDtos =  teacherWorkingDaysRepository.findAll().stream()
//                .map(mapperService::teacherWorkingDayToTeacherWorkingDayDto).toList();
//        ExerciseDto exerciseDto = exerciseService.findById(1).get();
//        return new TeachersScheduleMasterRequestDto(exerciseDto,teacherWorkingDayDtos);
//    }
}
