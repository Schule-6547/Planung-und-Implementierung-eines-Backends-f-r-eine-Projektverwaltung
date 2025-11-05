package de.szut.lf8_starter.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeWithSkillsDto {
    private Long id;
    private String lastName;
    private String firstName;
    private List<SkillDto> skillSet;

    @NoArgsConstructor
    @Getter
    @Setter
    public static class SkillDto {
        private String skill;
    }
}
