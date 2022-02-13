package ru.team.scheduler.oapi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.dto.RoleDto;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.oapi.services.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@Slf4j
public class RoleController {

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<RoleDto> getAllRoles(){
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public RoleDto getRoleById(@PathVariable("id") Integer roleId){
        return roleService.findById(roleId).orElseThrow(NotFoundException::new);
    }

    @GetMapping()
    public RoleDto getRoleByRoleName(@RequestParam(value = "name", required = false)
                                                 String roleName){
        return roleService.findByName(roleName).orElseThrow(NotFoundException::new);
    }

    @PostMapping()
    public RoleDto createRole(@RequestBody RoleDto roleDto){
        if(roleDto.getId()!=null){
            throw new IllegalArgumentException("Role id must be null");
        }
        log.info("Someone try to create new Role with name: {}", roleDto.getName());
        return roleService.save(roleDto).orElseThrow(NotFoundException::new);
    }

    @PutMapping
    public RoleDto updateRole(@RequestBody RoleDto roleDto){
        if(roleDto.getId()==null){
            throw new IllegalArgumentException("Role id must not be null");
        }
        return roleService.save(roleDto).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRoleById(@PathVariable("id") Integer roleId){
        roleService.deleteById(roleId);
    }

    @DeleteMapping()
     /*
    Do not use this shit
     */
    public ResponseEntity<String> deleteRoles(){
        roleService.deleteAll();
        return new ResponseEntity<>("You cannot delete all Roles", HttpStatus.BAD_REQUEST);
    }
}
