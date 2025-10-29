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
                entity.getEmployee(),
                entity.getCustomer(),
                entity.getCustomerContactPersonName(),
                entity.getComment(),
                entity.getStartDateTimestamp(),
                entity.getPlannedEndDateTimestamp(),
                entity.getRealEndDateTimestamp()
        );
    }

    public ProjectEntity mapCreateDtoToEntity(ProjectCreateDto dto) {
        var entity = new ProjectEntity();
        entity.setDesignation(dto.getDesignation());
        entity.setEmployee(dto.getEmployee());
        entity.setCustomer(dto.getCustomer());
        entity.setCustomerContactPersonName(dto.getCustomerContactPersonName());
        entity.setComment(dto.getComment());
        entity.setStartDateTimestamp(dto.getStartDateTimestamp());
        entity.setPlannedEndDateTimestamp(dto.getPlannedEndDateTimestamp());
        entity.setRealEndDateTimestamp(dto.getRealEndDateTimestamp());
        return entity;
    }
}
