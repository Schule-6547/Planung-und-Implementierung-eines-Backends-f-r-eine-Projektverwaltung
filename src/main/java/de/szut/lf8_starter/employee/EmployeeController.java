package de.szut.lf8_starter.employee;


import de.szut.lf8_starter.exceptionHandling.ResourceNotFoundException;
import de.szut.lf8_starter.employee.dto.EmployeeCreateDto;
import de.szut.lf8_starter.employee.dto.EmployeeGetDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/employee/")
public class EmployeeController implements EmployeeControllerOpenAPI {
    private final EmployeeService service;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService service, EmployeeMapper mappingService) {
        this.service = service;
        this.employeeMapper = mappingService;
    }


    @PostMapping
    public EmployeeGetDto create(@RequestBody @Valid EmployeeCreateDto employeeCreateDto) {
        EmployeeEntity employeeEntity = this.employeeMapper.mapCreateDtoToEntity(employeeCreateDto);
        employeeEntity = this.service.create(employeeEntity);
        return this.employeeMapper.mapToGetDto(employeeEntity);
    }


    @GetMapping
    public List<EmployeeGetDto> findAll() {
        return this.service
                .readAll()
                .stream()
                .map(this.employeeMapper::mapToGetDto)
                .collect(Collectors.toList());
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
}
