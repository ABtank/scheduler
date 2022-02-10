package ru.team.scheduler.oapi.services;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.team.scheduler.persist.entities.User;
import ru.team.scheduler.persist.repositories.UserRepository;

import java.util.Optional;

@Service
//@NoArgsConstructor
//@RequiredArgsConstructor
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByName(String username){
        return userRepository.findByFirstName(username);
    }

}
