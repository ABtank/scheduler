package ru.team.scheduler.oapi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.persist.entities.Discipline;
import ru.team.scheduler.persist.entities.Exercise;
import ru.team.scheduler.persist.entities.Lesson;
import ru.team.scheduler.persist.repositories.LessonRepository;
import ru.team.scheduler.persist.repositories.UserRepository;
import ru.team.scheduler.persist.responsesOfDataBase.LessonByIdResponse;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final ExerciseService exerciseService;
    private final DisciplineService disciplineService;
    private final UserRepository userRepository;

    @Override
    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    @Override
    public Optional<Lesson> findById(Integer id) {
        return lessonRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id, Principal principal) {
        lessonRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Lesson> save(Lesson lesson, Principal principal) {
        lessonRepository.save(lesson);
        return lessonRepository.findById(lesson.getId());
    }

    @Override
    public long count() {
        return lessonRepository.count();
    }

    @Override
    @Transactional
    public Optional<Lesson> update(Lesson updatedLesson, Principal principal) {
        Integer updatedLessonId = updatedLesson.getId();
        String teachersEmail = principal.getName();
        Optional<Lesson> lesson = lessonRepository.findById(updatedLessonId);
        if (lesson.isEmpty()) {
            throw new NotFoundException("Предмета с введенным id не существует!");
        }
        Optional<Exercise> exerciseOptional = exerciseService.findById(updatedLesson.getExercise().getId());
        if(exerciseOptional.isPresent()){
           Exercise exercise = exerciseOptional.get();
           Optional<Discipline> discipline = disciplineService.findById(exercise.getId());
            if (discipline.isEmpty()) {
                throw new NotFoundException("Дисциплины не существует!");
            }
        }
        else throw new NotFoundException("Лекции с введенным Id не существует");
        updatedLesson.setExercise(exerciseOptional.get());
        lessonRepository.save(updatedLesson);
        return lessonRepository.findById(updatedLesson.getId());
    }

    public Optional<LessonByIdResponse> getLessonById(Integer id) {
        return lessonRepository.getLessonById(id);
    }
}
