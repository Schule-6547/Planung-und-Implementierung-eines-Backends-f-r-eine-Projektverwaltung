package de.szut.lf8_starter.employee;

import de.szut.lf8_starter.employee.dto.EmployeeCreateDto;
import de.szut.lf8_starter.employee.dto.EmployeeGetDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public interface EmployeeControllerOpenAPI {

    @Operation(summary = "creates a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created employee",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeGetDto.class))}),
            @ApiResponse(responseCode = "400", description = "invalid JSON posted",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    EmployeeGetDto create(EmployeeCreateDto employeeCreateDto);

    @Operation(summary = "delivers a list of employee objects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "list of employees",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EmployeeGetDto.class)))}),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content)})
    List<EmployeeGetDto> findAll();

    @Operation(summary = "deletes a employee by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "delete successful"),
            @ApiResponse(responseCode = "401", description = "not authorized",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "resource not found",
                    content = @Content)})
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deleteProjectById(long id);
}
