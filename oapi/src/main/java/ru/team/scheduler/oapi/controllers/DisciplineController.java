package ru.team.scheduler.oapi.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.constants.SwaggerConstant;
import ru.team.scheduler.oapi.dto.DisciplineDto;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.oapi.services.DisciplineService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping("/api/v1/disciplines")
@Api(tags = {SwaggerConstant.API_DISCIPLINE})
@RestController
public class DisciplineController {
    private DisciplineService disciplineService;

    @Autowired
    public void setDisciplineService(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @ApiOperation(value = "Получить список Дисциплин", notes = "Получить список Дисциплин. Есть возможность отфильтровать по названию.")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Данные получены."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере."),
            @ApiResponse(responseCode = "400", description = "Запрос неверный."),
            @ApiResponse(responseCode = "404", description = "Адрес URL не найден."),
            @ApiResponse(responseCode = "403", description = "Вы не авторизованы. Авторизуйтесь и повторите еще раз."),
            @ApiResponse(responseCode = "401", description = "У вас не достаточно прав доступа."),
    })
    @GetMapping
    public List<DisciplineDto> getAllDisciplines(
            @ApiParam(name = "name", value = "Название Дисциплины или его часть", allowEmptyValue = true)
            @RequestParam Optional<String> name) {
        return disciplineService.findAll(name.orElse(""));
    }

    @ApiOperation(value = "Найти Дисциплину по id.", notes = "Дисциплина которую преподает учитель.", response = DisciplineDto.class)
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Дисциплина найдена."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере."),
            @ApiResponse(responseCode = "400", description = "Запрос неверный."),
            @ApiResponse(responseCode = "404", description = "Адрес URL не найден."),
            @ApiResponse(responseCode = "403", description = "Вы не авторизованы. Авторизуйтесь и повторите еще раз."),
            @ApiResponse(responseCode = "401", description = "У вас не достаточно прав доступа."),
    })
    @GetMapping(value = "/{id}")
    public DisciplineDto findById(@PathVariable("id") Integer id) {
        return disciplineService.findById(id).orElseThrow(NotFoundException::new);
    }

    @ApiOperation(value = "Создать новую Дисциплину.", notes = "Дисциплина которую преподает учитель.", response = DisciplineDto.class)
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Новая Дисциплина была сохранена."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере."),
            @ApiResponse(responseCode = "400", description = "Запрос неверный."),
            @ApiResponse(responseCode = "404", description = "Адрес URL не найден."),
            @ApiResponse(responseCode = "403", description = "Вы не авторизованы. Авторизуйтесь и повторите еще раз."),
            @ApiResponse(responseCode = "401", description = "У вас не достаточно прав доступа."),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DisciplineDto create(
            @ApiParam(name = "name", value = "Объект Дисциплина в формате Json", required = true)
            @RequestBody DisciplineDto disciplineDto) {
        disciplineDto.setId(null);
        return disciplineService.save(disciplineDto).orElseThrow(NotFoundException::new);
    }

    @ApiOperation(value = "Изменить существующую Дисциплину.", notes = "Изменить Дисциплину которую преподает учитель.", response = DisciplineDto.class)
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Дисциплина была изменена."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере."),
            @ApiResponse(responseCode = "400", description = "Запрос неверный."),
            @ApiResponse(responseCode = "404", description = "Адрес URL не найден."),
            @ApiResponse(responseCode = "403", description = "Вы не авторизованы. Авторизуйтесь и повторите еще раз."),
            @ApiResponse(responseCode = "401", description = "У вас не достаточно прав доступа."),
    })
    @PutMapping
    public DisciplineDto updateDiscipline(
            @ApiParam(name = "name", value = "Объект Дисциплина в формате Json", required = true)
            @RequestBody DisciplineDto disciplineDto) {
        if (disciplineDto.getId() == null) {
            throw new IllegalArgumentException("Id not found in the update request");
        }
        return disciplineService.save(disciplineDto).orElseThrow(NotFoundException::new);
    }

    @ApiIgnore
    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        disciplineService.deleteAll();
        return new ResponseEntity<>("-=You cannot delete all disciplines=-", HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Удалить Дисциплину.", notes = "Удалить Дисциплину которую преподает учитель.")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Дисциплина удалена."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере."),
            @ApiResponse(responseCode = "400", description = "Запрос неверный."),
            @ApiResponse(responseCode = "404", description = "Адрес URL не найден."),
            @ApiResponse(responseCode = "403", description = "Вы не авторизованы. Авторизуйтесь и повторите еще раз."),
            @ApiResponse(responseCode = "401", description = "У вас не достаточно прав доступа."),
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) {
        disciplineService.deleteById(id);
        log.info("-=OK=-");
    }
}
