package com.lacrimosica.keycloak;


import com.lacrimosica.statuses.ProjectStatus;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "COMPANY_PROJECTS")
public class Project {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(name = "SUPERVISOR_ID")
    private Long supervisorId;
    @Column(name = "PROJECT_NAME")
    private String name;

    @Column(name = "PROJECT_DESCRIPTION")
    private String description;
    @Column(name = "PROJECT_STATUS")
    private ProjectStatus status;

    @Column(name = "COMPANY_ID")
    private Long companyId;

    @Transient
    private Employee supervisor;
    @Transient
    private Company company;

    public Project() {}

    public Project(String name) {
        this.name = name;
        this.status = ProjectStatus.UNASSIGNED;
    }

    public Long getId() {
        return id;
    }

    public Long getSupervisorId() {
        return this.supervisorId;
    }

    public String getName() {
        return name;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Employee getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) &&
                Objects.equals(supervisorId, project.supervisorId) &&
                Objects.equals(name, project.name) &&
                status == project.status;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, supervisorId, name, status);
    }


    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", supervisorId=" + supervisorId +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
