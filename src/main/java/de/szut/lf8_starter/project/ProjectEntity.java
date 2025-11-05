package de.szut.lf8_starter.project;

import de.szut.lf8_starter.employee.EmployeeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String designation;

    @OneToMany
    private List<EmployeeEntity> employees;

    private long customer;

    private String customerContactPersonName;

    private String comment;

    private LocalDateTime startDate;

    private LocalDateTime plannedEndDate;

    private LocalDateTime realEndDate;

    @Column(name = "required_skill")
    private String requiredSkill;
}

