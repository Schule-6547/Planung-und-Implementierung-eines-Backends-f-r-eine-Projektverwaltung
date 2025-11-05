package de.szut.lf8_starter.project;

import de.szut.lf8_starter.employee.EmployeeEntity;
import de.szut.lf8_starter.project.exception.EmployeeAlreadyAssignedToProjectException;
import de.szut.lf8_starter.exceptionHandling.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository repository;
    private final EmployeeApiClient employeeApiClient;

    public ProjectService(ProjectRepository repository, EmployeeApiClient employeeApiClient) {
        this.repository = repository;
        this.employeeApiClient = employeeApiClient;
    }

    public ProjectEntity create(ProjectEntity entity) {
        validateIds(entity);
        return this.repository.save(entity);
    }

    public List<ProjectEntity> readAll() {
        return this.repository.findAll();
    }

    public ProjectEntity readById(long id) {
        Optional<ProjectEntity> optionalQualification = this.repository.findById(id);
        return optionalQualification.orElse(null);
    }
    //neu
    public ProjectEntity update(ProjectEntity entity) {
        var existingOpt = repository.findById(entity.getId());
        if (existingOpt.isEmpty()) {
            throw new ResourceNotFoundException("ProjectEntity not found on id = " + entity.getId());
        }
        validateIds(entity);
        ProjectEntity existing = existingOpt.get();
        existing.setDesignation(entity.getDesignation());
        existing.setEmployees(entity.getEmployees());
        existing.setCustomer(entity.getCustomer());
        existing.setCustomerContactPersonName(entity.getCustomerContactPersonName());
        existing.setComment(entity.getComment());
        existing.setStartDate(entity.getStartDate());
        existing.setPlannedEndDate(entity.getPlannedEndDate());
        existing.setRealEndDate(entity.getRealEndDate());
        existing.setRequiredSkill(entity.getRequiredSkill());
        return repository.save(existing);
    }
    public void delete(ProjectEntity entity) {
        this.repository.delete(entity);
    }

    private void validateIds(ProjectEntity entity) {
        if (entity.getCustomer() != 0 && !employeeApiClient.existsClientById(entity.getCustomer())) {
            throw new ResourceNotFoundException("Customer id not found: " + entity.getCustomer());
        }
        String requiredSkill = entity.getRequiredSkill();
        if (entity.getEmployees() != null) {
            for (EmployeeEntity worker : entity.getEmployees()) {
                Long workerId = worker.getId();
                if (workerId == null) continue;
                if (!employeeApiClient.existsEmployeeById(workerId)) {
                    throw new ResourceNotFoundException("Employee id not found: " + workerId);
                }
                if (requiredSkill != null && !requiredSkill.isBlank()) {
                    boolean ok = employeeApiClient.employeeHasSkillByFetchingEmployee(workerId, requiredSkill);
                    if (!ok) {
                        throw new ResourceNotFoundException(
                                "Employee " + workerId + " does not have required skill: " + requiredSkill);
                    }
                }
            }
        }
    }

    public void assignEmployeeToProject(ProjectEntity project, EmployeeEntity employee) {
        if (isAvailableInPeriod(project.getStartDate(), project.getPlannedEndDate(), employee)) {
            project.getEmployees().add(employee);
            repository.save(project);
        } else {
            throw new EmployeeAlreadyAssignedToProjectException("Employee " + employee.getId() + " is already assigned in this period.");
        }
    }

    public void unassignEmployeeToProject(ProjectEntity project, EmployeeEntity employee) {
        project.getEmployees().remove(employee);
        repository.save(project);
    }

    private boolean isAvailableInPeriod(LocalDateTime start, LocalDateTime end, EmployeeEntity employee) {
        return readAll().stream().noneMatch(project -> {
            if (project.getStartDate() == null || project.getPlannedEndDate() == null) return false;
            if (!project.getEmployees().contains(employee)) return false;
            return start.isAfter(project.getStartDate()) && end.isBefore(project.getPlannedEndDate());
        });
    }
}
