package ru.team.scheduler.oapi.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.team.scheduler.persist.entities.LessonsStudent;
import ru.team.scheduler.persist.entities.User;
import ru.team.scheduler.persist.repositories.LessonsStudentsRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Data
@RequiredArgsConstructor
@Service
@Slf4j
public class LessonsStudentsServiceImpl implements LessonsStudentsService {

    private final ModelMapper modelMapper;
    private final LessonsStudentsRepository lessonsStudentsRepository;
    private final MapperService mapperService;

    @Override
    public List<LessonsStudent> findAll() {
        return lessonsStudentsRepository.findAll();
    }

    @Override
    public Optional<LessonsStudent> findById(Integer id) {
        return lessonsStudentsRepository.findById(id);
    }


    @Override
    public void deleteById(Integer id, Principal principal) {
        lessonsStudentsRepository.deleteById(id);
    }

    @Override
    public Optional<LessonsStudent> save(LessonsStudent lessonsStudent, Principal principal) {
        return Optional.of(lessonsStudentsRepository.save(lessonsStudent));
    }


    @Override
    public long count() {
        return lessonsStudentsRepository.count();
    }

    public List<LessonsStudent> findAllByStudent(User student){
        return lessonsStudentsRepository.findAllByStudent(student);
    }
}
