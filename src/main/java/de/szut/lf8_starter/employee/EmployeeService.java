package de.szut.lf8_starter.employee;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public EmployeeEntity create(EmployeeEntity entity) {
        return this.repository.save(entity);
    }

    public List<EmployeeEntity> readAll() {
        return this.repository.findAll();
    }

    public EmployeeEntity readById(long id) {
        Optional<EmployeeEntity> optionalQualification = this.repository.findById(id);
        return optionalQualification.orElse(null);
    }


    public void delete(EmployeeEntity entity) {
        this.repository.delete(entity);
    }

/*    public List<ProjectEntity> findByMessage(String message) {
        return this.repository.findByMessage(message);
    }*/
}
