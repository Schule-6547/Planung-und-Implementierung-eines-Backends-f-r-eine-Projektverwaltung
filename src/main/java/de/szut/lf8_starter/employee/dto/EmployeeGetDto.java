package de.szut.lf8_starter.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class EmployeeGetDto {

    private long id;

    private String firstname;

    private String lastname;

}

