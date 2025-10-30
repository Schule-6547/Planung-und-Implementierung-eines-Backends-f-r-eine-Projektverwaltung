package de.szut.lf8_starter.employee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

