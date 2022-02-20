package ru.team.scheduler.oapi.services;

import ru.team.scheduler.oapi.dto.RoleDto;

import java.util.List;
import java.util.Map;

public interface RoleService extends BasicService<RoleDto,Integer> {
    //    RoleDto findRoleByUsers(List<UserDto> users);
List<RoleDto> findAll(Map<String, String> params);
}
