package com.lacrimosica.exceptions;

public class ProjectNotAssignedException extends RuntimeException {
    public ProjectNotAssignedException(Long idp) {
        super("Project with id: " + idp + "is not assigned to anyone");
    }
}
