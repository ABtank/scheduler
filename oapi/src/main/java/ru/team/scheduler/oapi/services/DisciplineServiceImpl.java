package ru.team.scheduler.oapi.services;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.team.scheduler.oapi.dto.DisciplineDto;
import ru.team.scheduler.persist.entities.Discipline;
import ru.team.scheduler.persist.repositories.DisciplineRepository;
import ru.team.scheduler.persist.repositories.specifications.DisciplineSpecification;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
@Slf4j
@Service
@NoArgsConstructor
public class DisciplineServiceImpl implements DisciplineService{
    private ModelMapper modelMapper;
    private DisciplineRepository disciplineRepository;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setDisciplineRepository(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    @Override
    public List<DisciplineDto> findAll() {
        return disciplineRepository.findAll().stream().map(obj -> modelMapper.map(obj, DisciplineDto.class)).collect(toList());
    }

    @Override
    public List<DisciplineDto> findAll(String name) {
        Specification<Discipline> spec = DisciplineSpecification.trueLiteral();
        if(name != null && !name.isBlank()){
            spec = spec.and(DisciplineSpecification.nameContains(name));
        }
        return disciplineRepository.findAll(spec).stream().map(obj -> modelMapper.map(obj, DisciplineDto.class)).collect(toList());
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
