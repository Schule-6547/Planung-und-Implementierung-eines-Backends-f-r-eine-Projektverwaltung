package de.szut.lf8_starter.project.dto;

import de.szut.lf8_starter.employee.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProjectGetDto {

    private long id;

    private String designation;

    private List<EmployeeEntity> employees;

    private long customer;

    private String customerContactPersonName;

    private String comment;

    private LocalDateTime startDate;

    private LocalDateTime plannedEndDate;

    private LocalDateTime realEndDate;

    private String requiredSkill;
}

