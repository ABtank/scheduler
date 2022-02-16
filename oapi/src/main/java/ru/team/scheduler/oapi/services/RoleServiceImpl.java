package ru.team.scheduler.oapi.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.team.scheduler.oapi.dto.RoleDto;
import ru.team.scheduler.persist.entities.Role;
import ru.team.scheduler.persist.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream()
                .map(RoleDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleDto> findById(Integer id) {
        return roleRepository.findById(id).map(RoleDto::new);
    }

    @Override
    public Optional<RoleDto> findByName(String name) {
        return roleRepository.findByName(name).map(RoleDto::new);
    }

    @Override
    public Optional<RoleDto> save(RoleDto o) {
        Role roleForSave = new Role();
        roleForSave.setName(o.getName());
        Role role = roleRepository.save(roleForSave);
        log.info("Someone save new Role: {}", role);
        return Optional.of(role).map(RoleDto::new);
    }

    @Override
    /*
    Do not use this shit
     */
    public void deleteAll() {
        log.error("Someone decided to delete all Roles");
    }

    @Override
    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
        log.error("Someone decided to delete the role with id: {}", id);
    }

    @Override
    public long count() {
        return roleRepository.count();
    }
}
