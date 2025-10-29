package de.szut.lf8_starter.project;

import org.springframework.stereotype.Service;

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

/*    public List<ProjectEntity> findByMessage(String message) {
        return this.repository.findByMessage(message);
    }*/
}
