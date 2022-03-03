package ru.team.scheduler.oapi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team.scheduler.oapi.dto.TeacherWorkingDayDto;
import ru.team.scheduler.persist.entities.TeacherWorkingDay;
import ru.team.scheduler.persist.repositories.TeacherWorkingDaysRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherWorkingDaysImpl implements TeacherWorkingDaysService {
    private final TeacherWorkingDaysRepository teacherWorkingDaysRepository;
    private final MapperService mapperService;

    @Override
    public List<TeacherWorkingDay> findAll() {
        return teacherWorkingDaysRepository.findAll();
    }

    @Override
    public Optional<TeacherWorkingDay> findById(Integer id) {
        return teacherWorkingDaysRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id, Principal principal) {
          teacherWorkingDaysRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<TeacherWorkingDay> save(TeacherWorkingDay o, Principal principal) {
        TeacherWorkingDay teacherWorkingDay = teacherWorkingDaysRepository
                .save(o);
        return findById(teacherWorkingDay.getId());
    }

    @Override
    public long count() {
        return teacherWorkingDaysRepository.count();
    }

    @Override
    public Optional<TeacherWorkingDay> update(TeacherWorkingDay teacherWorkingDay) {
        return Optional.empty();
    }
}
