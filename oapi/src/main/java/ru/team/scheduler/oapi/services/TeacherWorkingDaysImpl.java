package ru.team.scheduler.oapi.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.team.scheduler.oapi.dto.DisciplineDto;
import ru.team.scheduler.oapi.dto.TeacherWorkingDayDto;
import ru.team.scheduler.persist.entities.Discipline;
import ru.team.scheduler.persist.entities.TeacherWorkingDay;
import ru.team.scheduler.persist.repositories.TeacherWorkingDaysRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherWorkingDaysImpl implements TeacherWorkingDaysService {
    private final TeacherWorkingDaysRepository teacherWorkingDaysRepository;
    private final ModelMapper modelMapper;
    private final MapperService mapperService;

    @Override
    public List<TeacherWorkingDayDto> findAll() {
        return teacherWorkingDaysRepository.findAll()
                .stream().map(mapperService::teacherWorkingDayToTeacherWorkingDayDto).toList();
    }

    @Override
    public Optional<TeacherWorkingDayDto> findById(Integer id) {
        return teacherWorkingDaysRepository.findById(id). map(mapperService::teacherWorkingDayToTeacherWorkingDayDto);
    }

    @Override
    public void deleteById(Integer id) {
          teacherWorkingDaysRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        teacherWorkingDaysRepository.deleteAll();
    }

    @Override
    public Optional<TeacherWorkingDayDto> save(TeacherWorkingDayDto o) {
        TeacherWorkingDay teacherWorkingDay = teacherWorkingDaysRepository
                .save(mapperService.teacherWorkingDayDtoToTeacherWorkingDay(o));
        return findById(teacherWorkingDay.getId());
    }

    @Override
    public long count() {
        return teacherWorkingDaysRepository.count();
    }
}
