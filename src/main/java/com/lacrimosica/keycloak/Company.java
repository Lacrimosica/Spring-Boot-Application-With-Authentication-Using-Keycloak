package com.lacrimosica.keycloak;


import javax.persistence.*;
import java.util.HashMap;

@Entity
@Table(name = "COMPANY_TABLE")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "COMPANY_ID")
    private Long id;
    @Column(name = "COMPANY_NAME")
    private String name;

    @Column(name = "NUMBER_OF_EMPLOYEES")
    private Long numberOfEmployees;

    @Transient
    private HashMap<String , Employee> employees;
    @Transient
    private Employee CompanyAdmin;

    public Company() {}

    public Company(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(HashMap<String, Employee> employees) {
        this.employees = employees;
    }
}
