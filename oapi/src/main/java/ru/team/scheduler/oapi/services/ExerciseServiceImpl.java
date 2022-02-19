package ru.team.scheduler.oapi.services;


import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team.scheduler.oapi.dto.DisciplineDto;
import ru.team.scheduler.oapi.dto.ExerciseDto;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.persist.entities.Discipline;
import ru.team.scheduler.persist.entities.Exercise;
import ru.team.scheduler.persist.entities.User;
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
    public Optional<Exercise> save(Exercise o) {
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Exercise> save(Principal principal, Exercise exercise) {
        String disciplineTitle = exercise.getDiscipline().getName();
        String teachersEmail = principal.getName();
        Optional <Discipline> discipline = disciplineService.findByName(disciplineTitle);
        if (discipline.isPresent()) {
            exercise.setDiscipline(discipline.get());
        }
        else throw new NotFoundException("Дисциплина не найдена");
        if (userRepository.findByEmail(teachersEmail).isPresent()) {
            exercise.setTeacher(userRepository.findByEmail(teachersEmail).get());
        }
        else throw new NotFoundException("Учитель не найден");
        exercisesRepository.save(exercise);
        return findById(exercise.getId());
    }

    @Transactional
    @Override
    public Optional<Exercise> update(Principal principal, Exercise updatedExercise) {
        Integer updatedExerciseId = updatedExercise.getId();
       String teachersEmail = principal.getName();
       Optional<Exercise> exercise = exercisesRepository.findById(updatedExerciseId);
       if(exercise.isEmpty()) {
           throw new NotFoundException("Предмета с введенным id не существует!");
       }
       Optional<Discipline> discipline = disciplineService.findByName(updatedExercise.getDiscipline().getName());
       if(discipline.isEmpty()){
           throw new NotFoundException("Дисциплины с введенным id не существует!");
       }
        updatedExercise.setDiscipline(discipline.get());
        Optional<User> teacher = userRepository.findByEmail(teachersEmail);
        if (teacher.isPresent()) {
            updatedExercise.setTeacher(teacher.get());
        }
        else throw new NotFoundException("Учитель не найден");
         exercisesRepository.save(updatedExercise);
         return exercisesRepository.findById(updatedExercise.getId());
    }

    @Override
    public long count() {
        return exercisesRepository.count();
    }
}
