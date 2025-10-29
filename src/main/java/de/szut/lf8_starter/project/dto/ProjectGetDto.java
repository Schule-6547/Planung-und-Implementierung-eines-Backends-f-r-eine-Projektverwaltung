package de.szut.lf8_starter.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProjectGetDto {

    private long id;

    private String designation;

    private long employee;

    private long customer;

    private String customerContactPersonName;

    private String comment;

    private long startDateTimestamp;

    private long plannedEndDateTimestamp;

    private long realEndDateTimestamp;

}

