package de.szut.lf8_starter.repository;

import de.szut.lf8_starter.model.ProjectEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectEntryRepository extends JpaRepository<ProjectEntry, Long> {

}
