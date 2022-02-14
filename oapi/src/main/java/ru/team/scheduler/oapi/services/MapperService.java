package ru.team.scheduler.oapi.services;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.team.scheduler.oapi.dto.UserCreationDto;
import ru.team.scheduler.oapi.dto.UserDto;
import ru.team.scheduler.persist.entities.Role;
import ru.team.scheduler.persist.entities.User;

import static java.util.stream.Collectors.toList;

@Slf4j
@Component
public class MapperService {
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
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

    public User userDtoToUser (UserDto userDto, User user) {
                user.setEmail(userDto.getEmail());
                user.setPhone(userDto.getPhone());
                user.setFirstName(userDto.getFirstName());
                user.setMiddleName(userDto.getMiddleName());
                user.setLastName(userDto.getLastName());
        return user;
    }

    public User userCreationDTOtoUser(UserCreationDto userCreationDTO) {
        return new User(
                userCreationDTO.getEmail(),
                passwordEncoder.encode(userCreationDTO.getPassword())
        );
    }

    
}
