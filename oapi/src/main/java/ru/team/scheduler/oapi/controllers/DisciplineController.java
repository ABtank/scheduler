package ru.team.scheduler.oapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.dto.DisciplineDto;
import ru.team.scheduler.oapi.exseption.NotFoundException;
import ru.team.scheduler.oapi.services.DisciplineService;

import java.util.List;

@RequestMapping("/api/v1/discipline")
@RestController
public class DisciplineController {
    private final static Logger LOG = LoggerFactory.getLogger(DisciplineController.class);
    private DisciplineService disciplineService;

    @Autowired
    public void setDisciplineService(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping
    public List<DisciplineDto> getAllDisciplines() {
        return disciplineService.findAll();
    }

    @GetMapping(value = "/{id}")
    public DisciplineDto findById(@PathVariable("id") Integer id) {
        return disciplineService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DisciplineDto create(@RequestBody DisciplineDto disciplineDto) {
        System.out.println(disciplineDto);
        disciplineDto.setId(null);
        return disciplineService.save(disciplineDto).orElseThrow(NotFoundException::new);
    }

    @PutMapping
    public DisciplineDto updateDiscipline(@RequestBody DisciplineDto disciplineDto) {
        if (disciplineDto.getId() == null) {
            throw new IllegalArgumentException("Id not found in the update request");
        }
        return disciplineService.save(disciplineDto).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        disciplineService.deleteAll();
        return new ResponseEntity<>("-=You cannot delete all disciplines=-", HttpStatus.BAD_REQUEST);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) {
        disciplineService.deleteById(id);
        LOG.info("-=OK=-");
    }
}
