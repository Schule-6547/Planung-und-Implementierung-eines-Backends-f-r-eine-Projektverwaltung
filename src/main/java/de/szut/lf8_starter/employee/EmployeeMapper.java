package de.szut.lf8_starter.employee;


import de.szut.lf8_starter.employee.dto.EmployeeCreateDto;
import de.szut.lf8_starter.employee.dto.EmployeeGetDto;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {

    public EmployeeGetDto mapToGetDto(EmployeeEntity entity) {
        return new EmployeeGetDto(
                entity.getId(),
                entity.getFirstname(),
                entity.getLastname()
        );
    }

    public EmployeeEntity mapCreateDtoToEntity(EmployeeCreateDto dto) {
        var entity = new EmployeeEntity();
        entity.setFirstname(dto.getFirstname());
        entity.setLastname(dto.getLastname());
        return entity;
    }
}
