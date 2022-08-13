package com.lacrimosica.controllers;

import com.lacrimosica.services.EmployeeService;
import com.lacrimosica.keycloak.Project;
import com.lacrimosica.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/projects")
    ResponseEntity<List<Project>> all() {

        return ResponseEntity.status(HttpStatus.OK)
                .body(projectService.getAllProjects());
    }

    @GetMapping("/projects/{id}")
    ResponseEntity<Project> one(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(projectService.getProjectById(id));
    }

    @PostMapping("/projects")
    ResponseEntity<Project> newProject(@RequestBody Project newProject) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projectService.addProject(newProject));
    }

    @PutMapping("/projects/{idp}/assign/{ids}")
    ResponseEntity<?> assign(@PathVariable Long idp, @PathVariable Long ids) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(projectService.assignProject(
                        employeeService.getEmployee(ids),
                        projectService.getProjectById(idp)));
    }

    @PostMapping("/projects/{idp}/unassign")
    ResponseEntity<Project> unassign(@PathVariable Long idp) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(projectService.unassignProject(projectService.getProjectById(idp)));
    }

    @DeleteMapping("/projects/{idp}/delete")
    ResponseEntity<?> deleteProject(@PathVariable Long idp){
        return ResponseEntity.status(HttpStatus.OK)
                .body(projectService.deleteProject(projectService.getProjectById(idp)));
    }


}
