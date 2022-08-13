package com.lacrimosica.services;

import com.lacrimosica.keycloak.Employee;
import com.lacrimosica.keycloak.Project;
import com.lacrimosica.exceptions.ProjectAlreadyAssignedException;
import com.lacrimosica.exceptions.ProjectNotAssignedException;
import com.lacrimosica.exceptions.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lacrimosica.repositories.ProjectRepository;
import com.lacrimosica.statuses.ProjectStatus;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProjectService {

    static List<Project> projects = new ArrayList<>();

    @Autowired
    ProjectRepository projectRepository;

    @PostConstruct
    public void init() {
        projectRepository.saveAll(
                Stream.of(
                        (new Project("Project 1")),
                        (new Project("Project 2")),
                        (new Project("Project 3")),
                        (new Project("Project 4")),
                        (new Project("Project 5")),
                        (new Project("Project 6"))
                ).collect(Collectors.toList()));
    }


    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id){
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

    public Project getProjectByName(String name){
        return projectRepository.findAll().stream().filter( p -> p.getName() == name)
                .findFirst()
                .orElseThrow(() -> new ProjectNotFoundException(name));
    }

    boolean isAssigned(Project project){
        return project.getStatus() == ProjectStatus.ASSIGNED;
    }
    public Project assignProject(Employee supervisor, Project project){
        if(isAssigned(project)){
            throw new ProjectAlreadyAssignedException(project.getId() , supervisor.getId());
        }

        project.setSupervisor(supervisor);
        project.setSupervisorId(supervisor.getId());
        project.setStatus(ProjectStatus.ASSIGNED);
        supervisor.getProjects().put(project.getName(),project);

        return project;
    }

    public Project unassignProject(Project project){
        if(!isAssigned(project)){
            throw new ProjectNotAssignedException(project.getId());
        }
        project.setSupervisor(null);
        project.setSupervisorId(null);
        project.setStatus(ProjectStatus.UNASSIGNED);
        project.getSupervisor().getProjects().remove(project.getName());

        return project;
    }
    public Project addProject(Project project){
        return projectRepository.save(project);
    }

    public Project deleteProject(Project project){
        projectRepository.delete(project);
        return project;
    }


}
