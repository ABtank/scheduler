package ru.team.scheduler.oapi.services;

import ru.team.scheduler.persist.entities.Role;

import java.util.List;
import java.util.Map;

public interface RoleService extends BasicService<Role,Integer> {
    //    RoleDto findRoleByUsers(List<UserDto> users);
List<Role> findAll(Map<String, String> params);
}
