package de.szut.lf8_starter.project.exception;

public class EmployeeAlreadyAssignedToProjectException extends RuntimeException {
    public EmployeeAlreadyAssignedToProjectException(String message) {
        super(message);
    }
}
