package de.szut.lf8_starter.project.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProjectCreateDto {

    private String designation;

    private long employee;

    private long customer;

    private String customerContactPersonName;

    private String comment;

    private long startDateTimestamp;

    private long plannedEndDateTimestamp;

    private long realEndDateTimestamp;
}
