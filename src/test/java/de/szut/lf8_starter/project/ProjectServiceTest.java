package de.szut.lf8_starter.project;

import de.szut.lf8_starter.employee.EmployeeEntity;
import de.szut.lf8_starter.project.exception.EmployeeAlreadyAssignedToProjectException;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class ProjectServiceTest {
    @Test
    public void unassignEmployeeToProjectTest() {
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        ProjectService service = new ProjectService(projectRepository, mock(EmployeeApiClient.class));

        ProjectEntity project = mock(ProjectEntity.class);
        EmployeeEntity employee = mock(EmployeeEntity.class);
        List<EmployeeEntity> employees = new ArrayList<>();
        employees.add(employee);
        when(project.getEmployees()).thenReturn(employees);

        service.unassignEmployeeToProject(project, employee);

        assertEquals(0, employees.size());
        verify(project, times(1)).getEmployees();
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    public void assignEmployeeToProjectTest() {
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        ProjectService service = new ProjectService(projectRepository, mock(EmployeeApiClient.class));

        ProjectEntity project = mock(ProjectEntity.class);
        EmployeeEntity employee = mock(EmployeeEntity.class);
        List<EmployeeEntity> employees = new ArrayList<>();
        when(project.getEmployees()).thenReturn(employees);

        service.assignEmployeeToProject(project, employee);

        verify(project, times(1)).getStartDate();
        verify(project, times(1)).getPlannedEndDate();
        verify(project, times(1)).getEmployees();
//        if (isAvailableInPeriod(project.getStartDate(), project.getPlannedEndDate(), employee)) {
//            project.getEmployees().add(employee);
//            repository.save(project);
//        } else {
//            throw new EmployeeAlreadyAssignedToProjectException("Employee " + employee.getId() + " is already assigned in this period.");
//        }
    }

//    private boolean isAvailableInPeriod(LocalDateTime start, LocalDateTime end, EmployeeEntity employee) {
//        return readAll().stream().noneMatch(project -> {
//            if (project.getStartDate() == null || project.getPlannedEndDate() == null) return false;
//            if (!project.getEmployees().contains(employee)) return false;
//            return start.isAfter(project.getStartDate()) && end.isBefore(project.getPlannedEndDate());
//        });
//    }
}
