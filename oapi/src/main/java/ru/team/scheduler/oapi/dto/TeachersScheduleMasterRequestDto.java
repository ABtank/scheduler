package ru.team.scheduler.oapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeachersScheduleMasterRequestDto {
    private ExerciseDto exerciseDto;
    private List<TeacherWorkingDayDto> teacherWorkingDaysDtos;
}
