package com.lacrimosica.keycloak;

import java.util.HashMap;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE_TABLE")
public class Employee {

    @Column(name = "ID")
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String lastName;
    @Column(name = "ROLE")
    private String role;

    @Column(name = "COMPANY_ID")
    private Long companyId;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Transient
    private HashMap<String , Project> projects;
    public Employee() {}

    public Employee(String firstName, String lastName, String role) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {return this.firstName;}

    public String getLastName() {
        return this.lastName;
    }

    public String getRole() {
        return this.role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public HashMap<String , Project> getProjects() {
        return projects;
    }
    public void setProjects(HashMap<String , Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Employee))
            return false;
        Employee employee = (Employee) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.firstName, employee.firstName)
                && Objects.equals(this.lastName, employee.lastName) && Objects.equals(this.role, employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.role);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName
                + '\'' + ", role='" + this.role + '\'' + '}';
    }
}