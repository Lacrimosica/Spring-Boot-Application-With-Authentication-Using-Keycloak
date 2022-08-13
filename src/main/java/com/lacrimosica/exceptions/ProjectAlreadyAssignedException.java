package com.lacrimosica.exceptions;

public class ProjectAlreadyAssignedException extends RuntimeException {
    public ProjectAlreadyAssignedException(Long idp, Long ids) {
        super("Project with id: " + idp + "is already assigned to Employee with id: " + ids);
    }
}

