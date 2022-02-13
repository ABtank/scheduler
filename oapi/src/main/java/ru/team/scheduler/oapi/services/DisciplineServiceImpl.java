package ru.team.scheduler.oapi.services;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.team.scheduler.oapi.dto.DisciplineDto;
import ru.team.scheduler.persist.entities.Discipline;
import ru.team.scheduler.persist.repositories.DisciplineRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
@Slf4j
@Service
@RequiredArgsConstructor
public class DisciplineServiceImpl implements DisciplineService {
    private final ModelMapper modelMapper;
    private final DisciplineRepository disciplineRepository;

    @Override
    public List<DisciplineDto> findAll() {
        return disciplineRepository.findAll().stream().map(obj -> modelMapper.map(obj, DisciplineDto.class)).collect(toList());
    }

    @Override
    public Optional<DisciplineDto> findById(Integer id) {
        return disciplineRepository.findById(id).map(obj -> modelMapper.map(obj, DisciplineDto.class));
    }

    @Override
    public Optional<DisciplineDto> findByName(String name) {
        return disciplineRepository.findByName(name).map(obj -> modelMapper.map(obj, DisciplineDto.class));
    }

    @Override
    public Optional<Discipline> findEntityByName(String name){
        return disciplineRepository.findByName(name);
    }

    @Override
    public void deleteById(Integer id) {
        disciplineRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        log.error("Someone decided to delete all Disciplines");
    }

    @Override
    public Optional<DisciplineDto> save(DisciplineDto o) {
        Discipline discipline = disciplineRepository.save(modelMapper.map(o, Discipline.class));
        return findById(discipline.getId());
    }

    @Override
    public long count() {
        return disciplineRepository.count();
    }
}
