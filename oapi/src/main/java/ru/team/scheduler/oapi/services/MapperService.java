package ru.team.scheduler.oapi.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.team.scheduler.oapi.dto.*;
import ru.team.scheduler.persist.entities.*;
import ru.team.scheduler.persist.repositories.ExercisesRepository;
import ru.team.scheduler.persist.repositories.WeekdaysRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.util.stream.Collectors.summarizingDouble;
import static java.util.stream.Collectors.toList;

@Slf4j
@Component
@RequiredArgsConstructor
public class MapperService {
        private PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final WeekdaysRepository weekdaysRepository;
    private final ExercisesRepository exercisesRepository;


    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto userToDto(User user) {
        return new UserDto(user.getId(),
                user.getEmail(),
                user.getPhone(),
                user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .collect(toList()),
                user.getDtCreate(),
                user.getDtModify());
    }

    public User userDtoToUser(UserDto userDto, User user) {
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        return user;
    }

    public User userCreationDTOtoUser(UserCreationDto userCreationDTO) {
        return new User(userCreationDTO.getId(),
                userCreationDTO.getEmail(),
                "1"
//                passwordEncoder.encode(userCreationDTO.getPassword())
        );
    }

    public ExerciseDto exerciseToDto(Exercise exercise) {
        return new ExerciseDto(
                exercise.getId(),
                exercise.getName(),
                exercise.getDiscipline().getId(),
                exercise.getIsPersonal(),
                exercise.getDuration(),
                exercise.getQuantity(),
                exercise.getTeacher().getId(),
                exercise.getTeacher().getFirstName(),
                exercise.getTeacher().getLastName(),
                exercise.getDiscipline().getName()
        );
    }

    public Exercise exerciseDtoToExercise(ExerciseDto exerciseDto) {
        return new Exercise(
                exerciseDto.getId(),
                exerciseDto.getName(),
                exerciseDto.getIsPersonal(),
                exerciseDto.getDuration(),
                exerciseDto.getQuantity());
    }

    public TeacherWorkingDay teacherWorkingDayDtoToTeacherWorkingDay(TeacherWorkingDayDto teacherWorkingDaysDto) {
        Date dateStart = null;
        Date dateEnd = null;
        try {
            dateStart = new SimpleDateFormat("hh:mm").parse(teacherWorkingDaysDto.getTimeStart());
            dateEnd = new SimpleDateFormat("hh:mm").parse(teacherWorkingDaysDto.getTimeEnd());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        TeacherWorkingDay teacherWorkingDay = new TeacherWorkingDay(
                dateStart.toInstant(),
                dateEnd.toInstant()
        );
        teacherWorkingDay.setExercise(exercisesRepository.findByName(teacherWorkingDaysDto.getExerciseName()).get());
        teacherWorkingDay.setWeekday(weekdaysRepository.findByName(teacherWorkingDaysDto.getWeekDayName()).get());
        return teacherWorkingDay;
    }

    public TeacherWorkingDayDto teacherWorkingDayToTeacherWorkingDayDto(TeacherWorkingDay teacherWorkingDay) {
        return new TeacherWorkingDayDto(
                teacherWorkingDay.getId(),
                teacherWorkingDay.getWeekday().getId(),
                teacherWorkingDay.getExercise().getId(),
                teacherWorkingDay.getWeekday().getName(),
                teacherWorkingDay.getExercise().getName(),
                teacherWorkingDay.getTime_start().toString(),
                teacherWorkingDay.getTime_end().toString()
        );
    }
}
