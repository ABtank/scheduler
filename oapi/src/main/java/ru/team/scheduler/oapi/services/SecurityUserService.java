package ru.team.scheduler.oapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.team.scheduler.oapi.models.CustomUserDetails;
import ru.team.scheduler.persist.entities.Role;
import ru.team.scheduler.persist.entities.User;
import ru.team.scheduler.persist.repositories.UserRepository;

import java.util.Set;

@Service
public class SecurityUserService implements UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository repository) {
        this.userRepository = repository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder encoder) {
        this.passwordEncoder = encoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Неверные имя пользователя или пароль"));
        Set<Role> roles = user.getRoles();
        return new CustomUserDetails(user, roles);
    }

    public User getUserByCredentials(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
