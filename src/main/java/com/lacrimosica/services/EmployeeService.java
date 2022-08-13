package com.lacrimosica.services;

import com.lacrimosica.keycloak.Employee;
import com.lacrimosica.keycloak.Project;
import com.lacrimosica.exceptions.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lacrimosica.repositories.EmployeeRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeService {

    static List<Employee> employees = new ArrayList<>();

    @Autowired
    EmployeeRepository employeeRepository;


    @PostConstruct
    public void init() {
        employeeRepository.saveAll(
                Stream.of(
                        (new Employee( "Jesus", "Christ", "admin" )),
                        (new Employee("Steve", "Jobs", "companyAdmin")),
                        (new Employee("Steve", "wozniak", "companyAdmin")),
                        (new Employee("Tim", "Cook", "companyAdmin")),

                        (new Employee("Bill", "Gates", "companyAdmin")),

                        (new Employee("Mark", "Zuckerberg", "companyAdmin")),

                        (new Employee("Larry", "Page", "companyAdmin")),
                        (new Employee("Sergey", "Brin", "companyAdmin")),

                        (new Employee("Jeff", "Bezos", "companyAdmin")),
                        (new Employee("Larry", "Ellison", "companyAdmin")),

                        (new Employee("John", "Doe", "user")),
                        (new Employee("Jane", "Doe", "user")),
                        (new Employee("James", "Doe", "user")),
                        (new Employee("Janet", "Doe", "user")),
                        (new Employee("Jan", "Doe", "user"))
                ).collect(Collectors.toList()));
    }


    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee addEmployee(Employee newEmployee){
        return employeeRepository.save(newEmployee);
    }

    public Employee updateEmployee(Employee newEmployee, Long id){
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return employeeRepository.save(employee);
                }).orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });
    }

    public void deleteEmployee(Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepository.deleteById(id);
    }
    public List<Project> getAssignedProjects(Long id){

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return employee.getProjects().values().stream().collect(Collectors.toList());
    }



}
