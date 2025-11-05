package de.szut.lf8_starter.project;


import de.szut.lf8_starter.project.dto.ProjectCreateDto;
import de.szut.lf8_starter.project.dto.ProjectGetDto;
import org.springframework.stereotype.Service;

@Service
public class ProjectMapper {

    public ProjectGetDto mapToGetDto(ProjectEntity entity) {
        return new ProjectGetDto(
                entity.getId(),
                entity.getDesignation(),
                entity.getEmployees(),
                entity.getCustomer(),
                entity.getCustomerContactPersonName(),
                entity.getComment(),
                entity.getStartDate(),
                entity.getPlannedEndDate(),
                entity.getRealEndDate(),
                entity.getRequiredSkill()
        );
    }

    public ProjectEntity mapCreateDtoToEntity(ProjectCreateDto dto) {
        var entity = new ProjectEntity();
        entity.setDesignation(dto.getDesignation());
        entity.setEmployees(dto.getEmployees());
        entity.setCustomer(dto.getCustomer());
        entity.setCustomerContactPersonName(dto.getCustomerContactPersonName());
        entity.setComment(dto.getComment());
        entity.setStartDate(dto.getStartDate());
        entity.setPlannedEndDate(dto.getPlannedEndDate());
        entity.setRealEndDate(dto.getRealEndDate());
        entity.setRequiredSkill(dto.getRequiredSkill());
        return entity;
    }
}
