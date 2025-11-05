package de.szut.lf8_starter.project;

import de.szut.lf8_starter.project.dto.EmployeeWithSkillsDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class EmployeeApiClient {
    private final RestTemplate restTemplate;
    private final String baseUrl = "https://employee.szut.dev";

    public EmployeeApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean existsClientById(Long id) {
        try {
            restTemplate.getForEntity(baseUrl + "/clients/{id}", Void.class, id);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Client API error: " + e.getStatusCode(), e);
        }
    }

    public boolean existsEmployeeById(Long id) {
        try {
            restTemplate.getForEntity(baseUrl + "/employees/{id}", Void.class, id);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Employee API error: " + e.getStatusCode(), e);
        }
    }
    public EmployeeWithSkillsDto getEmployeeById(Long id) {
        try {
            return restTemplate.getForObject(baseUrl + "/employees/{id}", EmployeeWithSkillsDto.class, id);
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Worker API error: " + e.getStatusCode(), e);
        }
    }

    public boolean employeeHasSkillByFetchingEmployee(Long workerId, String skill) {
        EmployeeWithSkillsDto dto = getEmployeeById(workerId);
        if (dto == null || dto.getSkillSet() == null) return false;
        return dto.getSkillSet().stream()
                .anyMatch(s -> s.getSkill() != null && s.getSkill().equalsIgnoreCase(skill));
    }
}
