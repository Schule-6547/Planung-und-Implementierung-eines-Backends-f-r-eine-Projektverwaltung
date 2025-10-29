package de.szut.lf8_starter.employee;


import de.szut.lf8_starter.employee.dto.EmployeeCreateDto;
import de.szut.lf8_starter.employee.dto.EmployeeGetDto;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {

    public EmployeeGetDto mapToGetDto(EmployeeEntity entity) {
        return new EmployeeGetDto(
                entity.getId(),
                entity.getDesignation(),
                entity.getEmployees(),
                entity.getCustomer(),
                entity.getCustomerContactPersonName(),
                entity.getComment(),
                entity.getStartDateTimestamp(),
                entity.getPlannedEndDateTimestamp(),
                entity.getRealEndDateTimestamp()
        );
    }

    public EmployeeEntity mapCreateDtoToEntity(EmployeeCreateDto dto) {
        var entity = new EmployeeEntity();
        entity.setDesignation(dto.getDesignation());
        entity.setEmployees(dto.getEmployees());
        entity.setCustomer(dto.getCustomer());
        entity.setCustomerContactPersonName(dto.getCustomerContactPersonName());
        entity.setComment(dto.getComment());
        entity.setStartDateTimestamp(dto.getStartDateTimestamp());
        entity.setPlannedEndDateTimestamp(dto.getPlannedEndDateTimestamp());
        entity.setRealEndDateTimestamp(dto.getRealEndDateTimestamp());
        return entity;
    }
}
