package ru.team.scheduler.oapi.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team.scheduler.oapi.dto.DisciplineDto;
import ru.team.scheduler.oapi.dto.ExerciseDto;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.persist.entities.Discipline;
import ru.team.scheduler.persist.entities.Exercise;
import ru.team.scheduler.persist.repositories.ExercisesRepository;
import ru.team.scheduler.persist.repositories.UserRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService{
    private final ExercisesRepository exercisesRepository;
    private final DisciplineService disciplineService;
    private final UserRepository userRepository;
    private final MapperService mapperService;

    @Override
    public List<Exercise> findAll() {
        return exercisesRepository.findAll();
    }

    @Override
    public Optional<Exercise> findById(Integer id) {
        return exercisesRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        exercisesRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        exercisesRepository.deleteAll();
    }

    @Override
    public Optional<ExerciseDto> save(ExerciseDto o) {
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Exercise> save(Principal principal, Exercise exercise) {
        String disciplineTitle = exercise.getDiscipline().getName();
        String teachersEmail = principal.getName();
        Optional <Discipline> discipline = disciplineService.findByName(disciplineTitle);
        if (discipline.isEmpty()){
            disciplineService.save(discipline);
        }
        exercise.setDiscipline(disciplineService.findEntityByName(disciplineTitle).get());
        if (userRepository.findByEmail(teachersEmail).isPresent()) {
            exercise.setTeacher(userRepository.findByEmail(teachersEmail).get());
        }
        else throw new NotFoundException("Учитель не найден");
        exercisesRepository.save(exercise);
        return findById(exercise.getId());
    }

    @Override
    public long count() {
        return exercisesRepository.count();
    }
}
