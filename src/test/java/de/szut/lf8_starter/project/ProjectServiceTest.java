package de.szut.lf8_starter.project;

import de.szut.lf8_starter.employee.EmployeeEntity;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
}
