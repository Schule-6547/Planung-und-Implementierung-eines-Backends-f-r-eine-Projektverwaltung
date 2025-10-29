package de.szut.lf8_starter.project;


import de.szut.lf8_starter.exceptionHandling.ResourceNotFoundException;
import de.szut.lf8_starter.project.dto.ProjectCreateDto;
import de.szut.lf8_starter.project.dto.ProjectGetDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/hello/")
public class ProjectController implements ProjectControllerOpenAPI {
    private final ProjectService service;
    private final ProjectMapper projectMapper;

    public ProjectController(ProjectService service, ProjectMapper mappingService) {
        this.service = service;
        this.projectMapper = mappingService;
    }


    @PostMapping
    public ProjectGetDto create(@RequestBody @Valid ProjectCreateDto helloCreateDto) {
        ProjectEntity projectEntity = this.projectMapper.mapCreateDtoToEntity(helloCreateDto);
        projectEntity = this.service.create(projectEntity);
        return this.projectMapper.mapToGetDto(projectEntity);
    }


    @GetMapping
    public List<ProjectGetDto> findAll() {
        return this.service
                .readAll()
                .stream()
                .map(e -> this.projectMapper.mapToGetDto(e))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteHelloById(@PathVariable long id) {
        var entity = this.service.readById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("HelloEntity not found on id = " + id);
        } else {
            this.service.delete(entity);
        }
    }


    @GetMapping("/findByMessage")
    public List<ProjectGetDto> findAllEmployeesByQualification(@RequestParam String message) {
        return this.service
                .findByMessage(message)
                .stream()
                .map(e -> this.projectMapper.mapToGetDto(e))
                .collect(Collectors.toList());
    }
}
