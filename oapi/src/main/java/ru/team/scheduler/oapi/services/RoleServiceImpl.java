package ru.team.scheduler.oapi.services;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.team.scheduler.oapi.dto.RoleDto;
import ru.team.scheduler.persist.entities.Role;
import ru.team.scheduler.persist.repositories.RoleRepository;
import ru.team.scheduler.persist.repositories.specifications.RoleSpecification;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

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
    public Optional<RoleDto> save(RoleDto o) {
        Role role = roleRepository.save(modelMapper.map(o, Role.class));
        return findById(role.getId());
    }

    @Override
    public void deleteAll() {
        log.error("Someone decided to delete all Roles");
    }

    @Override
    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public long count() {
        return roleRepository.count();
    }

    @Override
    public List<RoleDto> findAll(Map<String, String> params) {
        Specification<Role> spec = RoleSpecification.trueLiteral();
        if(!params.get("name").isBlank()){
            spec = spec.and(RoleSpecification.nameContains(params.get("name")));
        }
        if(!params.get("descr").isBlank()){
            spec = spec.and(RoleSpecification.descriptionContains(params.get("descr")));
        }
        return roleRepository.findAll(spec).stream()
                .map(RoleDto::new)
                .collect(Collectors.toList());
    }
}
