package de.szut.lf8_starter.controller;

import de.szut.lf8_starter.model.ProjectEntry;
import de.szut.lf8_starter.repository.ProjectEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectEntryController {
    @Autowired
    private ProjectEntryRepository repository;

    //Create
    @PostMapping
    public ResponseEntity<ProjectEntry> createGuestbookEntry(@RequestBody ProjectEntry entry) {
        entry = repository.save(entry);
        return new ResponseEntity(entry, HttpStatus.CREATED);
    }
}
