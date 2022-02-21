package ru.team.scheduler.oapi.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.team.scheduler.oapi.dto.LessonsStudentsDto;
import ru.team.scheduler.persist.entities.LessonsStudent;
import ru.team.scheduler.persist.entities.User;
import ru.team.scheduler.persist.repositories.LessonsStudentsRepository;


import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@Service
@Slf4j
public class LessonsStudentsServiceImpl implements LessonsStudentsService {

    private final ModelMapper modelMapper;
    private final LessonsStudentsRepository lessonsStudentsRepository;

    @Override
    public List<LessonsStudentsDto> findAll() {
        return lessonsStudentsRepository.findAll().stream().map(LessonsStudentsDto::new).collect(Collectors.toList());
    }

    @Override
    public Optional<LessonsStudentsDto> findById(Integer id) {
        return lessonsStudentsRepository.findById(id).map(LessonsStudentsDto::new);
    }


    @Override
    public void deleteById(Integer id, Principal principal) {
        lessonsStudentsRepository.deleteById(id);
    }

    @Override
    public Optional<LessonsStudentsDto> save(LessonsStudentsDto o, Principal principal) {
        LessonsStudent lessonsStudent = lessonsStudentsRepository.save(modelMapper.map(o, LessonsStudent.class));
        return findById(lessonsStudent.getId());
    }

    @Override
    public long count() {
        return lessonsStudentsRepository.count();
    }

    public List<LessonsStudent> findAllByStudent(User student){
        return lessonsStudentsRepository.findAllByStudent(student);
    }
}
