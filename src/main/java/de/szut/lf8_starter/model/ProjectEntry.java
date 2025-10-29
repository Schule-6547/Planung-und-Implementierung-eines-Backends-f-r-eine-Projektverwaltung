package de.szut.lf8_starter.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectEntry {
    @id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "entry_generator", sequenceName = "entry_seq")
    private long id;
    //Project name
    private String name;

    @ElementCollection
    private List<Long> workerIds = new ArrayList<>();

    @ElementCollection
    private List<Long> clientIds = new ArrayList<>();
    //Person responsible at the customer's site (first name, last name)
    private String customerPerson;

    private String Comment;

    private Date startDate;

    private Date plannedEndDate;

    private Date actualEndDate;

}
