package ru.team.scheduler.oapi.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team.scheduler.oapi.constants.SwaggerConstant;
import ru.team.scheduler.oapi.dto.DisciplineDto;
import ru.team.scheduler.oapi.exceptions.NotFoundException;
import ru.team.scheduler.oapi.services.DisciplineService;
import ru.team.scheduler.persist.entities.Discipline;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequestMapping("/api/v1/disciplines")
@Api(tags = {SwaggerConstant.API_DISCIPLINE})
@RestController
public class DisciplineController {
    private DisciplineService disciplineService;
    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

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
            @RequestParam Optional<String> name, @ApiIgnore Principal principal) {
        return disciplineService.findAll(name.orElse(""))
                .stream()
                .map(this::EntityToDto)
                .collect(toList());
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
        return disciplineService.findById(id)
                .map(this::EntityToDto)
                .orElseThrow(NotFoundException::new);
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
            @RequestBody DisciplineDto disciplineDto, @ApiIgnore Principal principal) {
        disciplineDto.setId(null);
        return disciplineService
                .save(DtoToEntity(disciplineDto), principal)
                .map(this::EntityToDto)
                .orElseThrow(NotFoundException::new);
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
            @RequestBody DisciplineDto disciplineDto, @ApiIgnore Principal principal) {
        if (disciplineDto.getId() == null) {
            throw new IllegalArgumentException("Id not found in the update request");
        }
        return disciplineService
                .save(DtoToEntity(disciplineDto), principal)
                .map(this::EntityToDto)
                .orElseThrow(NotFoundException::new);
    }

    @ApiIgnore
    @DeleteMapping
    public ResponseEntity<String> deleteAll(@ApiIgnore Principal principal) {
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
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id,@ApiIgnore Principal principal) {
        disciplineService.deleteById(id, principal);
        Discipline discipline = disciplineService.findById(id).orElse(null);
        return ResponseEntity.ok((discipline != null)?HttpStatus.CONFLICT:HttpStatus.OK);
    }


    private Discipline DtoToEntity(DisciplineDto dto) {
        return modelMapper.map(dto, Discipline.class);
    }

    private DisciplineDto EntityToDto(Discipline dto) {
        return modelMapper.map(dto, DisciplineDto.class);
    }
}
