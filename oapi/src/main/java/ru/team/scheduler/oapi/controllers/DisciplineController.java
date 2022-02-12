package ru.team.scheduler.oapi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.dto.DisciplineDto;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.oapi.services.DisciplineService;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1/disciplines")
@RestController
public class DisciplineController {
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
        return disciplineService.save(disciplineDto).orElseThrow(NotFoundException::new);
    }

    @PutMapping
    public DisciplineDto updateDiscipline(@RequestBody DisciplineDto disciplineDto) {
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
        log.info("-=OK=-");
    }
}
