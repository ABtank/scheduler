package ru.team.scheduler.oapi.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.dto.ExerciseDto;
import ru.team.scheduler.oapi.dto.TeacherWorkingDayDto;
import ru.team.scheduler.oapi.dto.TeachersScheduleMasterRequestDto;
import ru.team.scheduler.oapi.dto.TeachersScheduleMasterResponseDto;
import ru.team.scheduler.oapi.services.ExerciseService;
import ru.team.scheduler.oapi.services.MapperService;
import ru.team.scheduler.oapi.services.TeachersScheduleMasterService;
import ru.team.scheduler.persist.repositories.TeacherWorkingDaysRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/teachersScheduleMaster")
@RequiredArgsConstructor
public class TeachersScheduleMasterController {
    private final TeachersScheduleMasterService teachersScheduleMasterService;
//    private final TeacherWorkingDaysRepository teacherWorkingDaysRepository;
//    private final ExerciseService exerciseService;
//    private final MapperService mapperService;

    @PostMapping
    public void createNewTeacherSchedule
            (@RequestBody TeachersScheduleMasterRequestDto teachersScheduleMasterRequestDto){
            teachersScheduleMasterService.creteNewTeachersSchedule(teachersScheduleMasterRequestDto);
    }

//    @GetMapping
//    public TeachersScheduleMasterRequestDto  test(){
//        List<TeacherWorkingDayDto> teacherWorkingDayDtos =  teacherWorkingDaysRepository.findAll().stream()
//                .map(mapperService::teacherWorkingDayToTeacherWorkingDayDto).toList();
//        ExerciseDto exerciseDto = exerciseService.findById(1).get();
//        return new TeachersScheduleMasterRequestDto(exerciseDto,teacherWorkingDayDtos);
//    }
}
