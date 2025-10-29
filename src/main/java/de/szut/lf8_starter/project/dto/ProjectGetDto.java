package de.szut.lf8_starter.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProjectGetDto {

    private long id;

    private String designation;

    private List<Long> employees;

    private long customer;

    private String customerContactPersonName;

    private String comment;

    private long startDateTimestamp;

    private long plannedEndDateTimestamp;

    private long realEndDateTimestamp;

}

