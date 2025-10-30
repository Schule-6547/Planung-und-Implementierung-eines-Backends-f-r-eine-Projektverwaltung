package de.szut.lf8_starter.project;


import de.szut.lf8_starter.employee.EmployeeEntity;
import de.szut.lf8_starter.exceptionHandling.ResourceNotFoundException;
import de.szut.lf8_starter.project.dto.ProjectCreateDto;
import de.szut.lf8_starter.project.dto.ProjectGetDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/project/")
public class ProjectController implements ProjectControllerOpenAPI {
    private final ProjectService service;
    private final ProjectMapper projectMapper;
    private ProjectRepository repository;

    public ProjectController(ProjectService service, ProjectMapper mappingService) {
        this.service = service;
        this.projectMapper = mappingService;
    }


    @PostMapping
    public ProjectGetDto create(@RequestBody @Valid ProjectCreateDto projectCreateDto) {
        ProjectEntity projectEntity = this.projectMapper.mapCreateDtoToEntity(projectCreateDto);
        projectEntity = this.service.create(projectEntity);
        return this.projectMapper.mapToGetDto(projectEntity);
    }


    @GetMapping
    public List<ProjectGetDto> findAll() {
        return this.service
                .readAll()
                .stream()
                .map(this.projectMapper::mapToGetDto)
                .collect(Collectors.toList());
    }

    @PutMapping
    public ResponseEntity<ProjectEntity> updateProjectById(@RequestBody ProjectEntity entryToUpdate) {
        Long id = entryToUpdate.getId();
        Optional<ProjectEntity> response = repository.findById(id)
                .map(entry -> {
                    entry.setDesignation(entryToUpdate.getDesignation());
                    entry.setEmployees(entryToUpdate.getEmployees());
                    entry.setCustomer(entryToUpdate.getCustomer());
                    entry.setCustomerContactPersonName(entryToUpdate.getCustomerContactPersonName());
                    entry.setComment(entryToUpdate.getComment());
                    entry.setStartDate(entryToUpdate.getStartDate());
                    entry.setPlannedEndDate(entryToUpdate.getPlannedEndDate());
                    entry.setRealEndDate(entryToUpdate.getRealEndDate());
                    return repository.save(entry);
                });
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public void deleteProjectById(@PathVariable long id) {
        var entity = this.service.readById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("ProjectEntity not found on id = " + id);
        } else {
            this.service.delete(entity);
        }
    }

    @PutMapping("/assignEmployee")
    public void assignEmployeeToProject(ProjectEntity project, EmployeeEntity employee) {
        service.assignEmployeeToProject(project, employee);
    }

    @PutMapping("/unassignEmployee")
    public void unassignEmployeeToProject(ProjectEntity project, EmployeeEntity employee) {
        service.unassignEmployeeToProject(project, employee);
    }
}
