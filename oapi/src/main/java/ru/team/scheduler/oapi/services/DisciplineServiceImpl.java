package ru.team.scheduler.oapi.services;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.team.scheduler.persist.entities.Discipline;
import ru.team.scheduler.persist.repositories.DisciplineRepository;
import ru.team.scheduler.persist.repositories.specifications.DisciplineSpecification;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@NoArgsConstructor
public class DisciplineServiceImpl implements DisciplineService{
    private DisciplineRepository disciplineRepository;

    @Autowired
    public void setDisciplineRepository(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    @Override
    public List<Discipline> findAll() {
        return disciplineRepository.findAll();
    }

    @Override
    public List<Discipline> findAll(String name) {
        Specification<Discipline> spec = DisciplineSpecification.trueLiteral();
        if(name != null && !name.isBlank()){
            spec = spec.and(DisciplineSpecification.nameContains(name));
        }
        return disciplineRepository.findAll(spec, Sort.by(Sort.Direction.ASC,"name"));
    }

    @Override
    public Optional<Discipline> findById(Integer id) {
        return disciplineRepository.findById(id);
    }

    @Override
    public Optional<Discipline> findByName(String name) {
        return disciplineRepository.findByName(name);
    }

    @Override
    public void deleteById(Integer id, Principal principal) {
        disciplineRepository.deleteById(id);
    }

    @Override
    public Optional<Discipline> save(Discipline o, Principal principal) {
        Discipline discipline = disciplineRepository.save(o);
        return findById(discipline.getId());
    }

    @Override
    public long count() {
        return disciplineRepository.count();
    }
}
