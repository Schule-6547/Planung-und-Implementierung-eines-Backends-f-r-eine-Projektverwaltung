package de.szut.lf8_starter.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ProjectCreateDto {

    private String designation;

    private List<Long> employees;

    private long customer;

    private String customerContactPersonName;

    private String comment;

    private long startDateTimestamp;

    private long plannedEndDateTimestamp;

    private long realEndDateTimestamp;
}
