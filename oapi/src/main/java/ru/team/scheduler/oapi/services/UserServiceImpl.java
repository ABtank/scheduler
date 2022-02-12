package ru.team.scheduler.oapi.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.team.scheduler.oapi.dto.UserCreationDto;
import ru.team.scheduler.oapi.dto.UserDto;
import ru.team.scheduler.persist.entities.Exercise;
import ru.team.scheduler.persist.entities.Lesson;
import ru.team.scheduler.persist.entities.User;
import ru.team.scheduler.persist.repositories.RoleRepository;
import ru.team.scheduler.persist.repositories.UserRepository;
import ru.team.scheduler.persist.repositories.specifications.UserSpecification;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private MapperService mapperService;

    @Autowired
    public void setMapperService(MapperService mapperService) {
        this.mapperService = mapperService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<User> findByName(String username) {
        return userRepository.findByFirstName(username);
    }

    @Override
    public List<UserDto> findAll() {
        for (User u : userRepository.findAll()) {
            System.out.println(u.getFirstName() + " " + u.getRoles());
            String discipline = (!u.getDisciplines().isEmpty()) ? u.getDisciplines().stream().findFirst().get().getName() : "";
            System.out.println("Где я учитель " + discipline);
            u.getExercises().forEach(e -> {
                if (Objects.equals(e.getTeacher().getId(), u.getId())) {
                    System.out.println("Exercise:" + e.getName());
                }
                e.getLessons().forEach(l -> {
                    if (Objects.equals(l.getExercise().getTeacher().getId(), u.getId()))
                        System.out.println("    Lesson: " + l.getName());
                });
            });
            System.out.println("Где я ученик");
            HashMap<Exercise, List<Lesson>> map = new HashMap<>();
            u.getLessons().forEach(l -> map.put(l.getExercise(), new ArrayList<>() {
            }));
            u.getLessons().forEach(l -> map.get(l.getExercise()).add(l));
            map.forEach((key, value) -> {
                System.out.println("Exercise:" + key.getName());
                value.forEach(l -> {
                    if (!Objects.equals(l.getExercise().getTeacher().getId(), u.getId()))
                        System.out.println("    Lesson: " + l.getName());
                });
            });
            System.out.println("-========-");
        }
        return userRepository.findAll()
                .stream()
                .map(mapperService::userToDto)
                .collect(toList());
    }

    @Override
    public Optional<UserDto> findById(Integer id) {
        return userRepository.findById(id).map(mapperService::userToDto);
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        return userRepository.findByEmail(email).map(mapperService::userToDto);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserDto> save(UserCreationDto userCreationDTO) {
        User user = mapperService.userCreationDTOtoUser(userCreationDTO);
        userCreationDTO.getRoles()
                .stream()
                .map(role -> roleRepository.findByName(role).get())
                .forEach(user.getRoles()::add);
        return findById(userRepository.save(user).getId());
    }

    @Override
    public Optional<UserDto> update(UserDto userDTO) {
        User user = userRepository.getById(userDTO.getId());
        user = mapperService.userDtoToUser(userDTO,user);
        if(!userDTO.getRoles().isEmpty()) {
            userDTO.getRoles()
                    .stream()
                    .map(role -> roleRepository.findByName(role).get())
                    .forEach(user.getRoles()::add);
        }
        return findById(userRepository.save(user).getId());
    }

    @Override
    public void deleteAll() {
        log.error("Someone decided to delete all Users");
    }

    @Override
    public Page<UserDto> findAll(Map<String, String> params, PageRequest pageRequest) {
        Specification<User> spec = UserSpecification.trueLiteral();
        if (params.containsKey("email_filter") && !params.get("email_filter").isBlank()) {
            spec = spec.and(UserSpecification.emailContains(params.get("email_filter")));
        }
        return userRepository.findAll(spec, pageRequest).map(mapperService::userToDto);
    }

    @Override
    public List<UserDto> findAll(Specification<User> spec) {
        return userRepository.findAll(spec).stream().map(mapperService::userToDto).collect(toList());
    }

    @Override
    public boolean checkIsUnique(String email, Integer id) {
        Specification<User> spec = UserSpecification.trueLiteral();
        spec = spec.and(UserSpecification.findByEmail(email));
        if (id != null) {
            spec = spec.and(UserSpecification.idNotEqual(id));
        }
        List<UserDto> checkEquals = findAll(spec);
        return checkEquals.isEmpty();
    }

    @Override
    public long count() {
        return userRepository.count();
    }
}
