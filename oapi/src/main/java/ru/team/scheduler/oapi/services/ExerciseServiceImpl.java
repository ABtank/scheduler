package ru.team.scheduler.oapi.services;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.team.scheduler.oapi.dto.DisciplineDto;
import ru.team.scheduler.oapi.dto.ExerciseDto;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
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
    public List<ExerciseDto> findAll() {
        return exercisesRepository.findAll().stream().map(mapperService::exerciseToDto).toList();
    }

    @Override
    public Optional<ExerciseDto> findById(Integer id) {
        return exercisesRepository.findById(id).map(mapperService::exerciseToDto);
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
    public Optional<ExerciseDto> save(Principal principal, ExerciseDto exerciseDto) {
        Exercise exercise = mapperService.exerciseDtoToExercise(exerciseDto);
        String disciplineTitle = exerciseDto.getDiscipline();
        String teachersEmail = principal.getName();
        DisciplineDto disciplineDto = new DisciplineDto(disciplineTitle);
        if (disciplineService.findEntityByName(disciplineTitle).isEmpty()){
            disciplineService.save(disciplineDto);
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
