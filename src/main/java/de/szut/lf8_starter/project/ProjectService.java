package de.szut.lf8_starter.project;

import de.szut.lf8_starter.employee.EmployeeEntity;
import de.szut.lf8_starter.project.exception.EmployeeAlreadyAssignedToProjectException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public ProjectEntity create(ProjectEntity entity) {
        return this.repository.save(entity);
    }

    public List<ProjectEntity> readAll() {
        return this.repository.findAll();
    }

    public ProjectEntity readById(long id) {
        Optional<ProjectEntity> optionalQualification = this.repository.findById(id);
        return optionalQualification.orElse(null);
    }


    public void delete(ProjectEntity entity) {
        this.repository.delete(entity);
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
