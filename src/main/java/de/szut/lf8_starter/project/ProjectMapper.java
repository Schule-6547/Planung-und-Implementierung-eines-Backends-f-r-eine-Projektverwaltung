package de.szut.lf8_starter.project;


import de.szut.lf8_starter.project.dto.ProjectCreateDto;
import de.szut.lf8_starter.project.dto.ProjectGetDto;
import org.springframework.stereotype.Service;

@Service
public class ProjectMapper {

    public ProjectGetDto mapToGetDto(ProjectEntity entity) {
        return new ProjectGetDto(entity.getId(), entity.getMessage());
    }

    public ProjectEntity mapCreateDtoToEntity(ProjectCreateDto dto) {
        var entity = new ProjectEntity();
        entity.setMessage(dto.getMessage());
        return entity;
    }
}
