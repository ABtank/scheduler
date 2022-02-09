package ru.team.scheduler.oapi.services;

import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.team.scheduler.oapi.dto.DisciplineDto;
import ru.team.scheduler.persist.entities.Discipline;
import ru.team.scheduler.persist.repositories.DisciplineRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
@Service
@NoArgsConstructor
public class DisciplineServiceImpl implements DisciplineService {
    private final static Logger LOG = LoggerFactory.getLogger(DisciplineServiceImpl.class);
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
    public Optional<DisciplineDto> findById(Integer id) {
        return disciplineRepository.findById(id).map(obj -> modelMapper.map(obj, DisciplineDto.class));
    }

    @Override
    public Optional<DisciplineDto> findByName(String name) {
        return disciplineRepository.findByName(name).map(obj -> modelMapper.map(obj, DisciplineDto.class));
    }

    @Override
    public void deleteById(Integer id) {
        disciplineRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        LOG.error("Someone decided to delete all Disciplines");
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
