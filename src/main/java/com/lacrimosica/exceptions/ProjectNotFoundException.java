package com.lacrimosica.exceptions;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(Long id) {
        super("Could not find employee " + id);
    }

    public ProjectNotFoundException(String name) {
        super("Could not find project " + name);
    }

}
