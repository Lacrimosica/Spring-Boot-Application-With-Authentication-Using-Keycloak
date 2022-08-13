package com.lacrimosica.controllers;

import java.util.List;

import com.lacrimosica.keycloak.Employee;
import com.lacrimosica.services.EmployeeService;
import com.lacrimosica.keycloak.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    @RolesAllowed({"admin", "companyAdmin"})
    ResponseEntity<List<Employee>> all() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployees());
    }

    @PostMapping("/employees")
    @RolesAllowed({"admin", "companyAdmin"})
    ResponseEntity<Employee> newEmployee(@RequestBody Employee newEmployee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployee(newEmployee));
    }

    @GetMapping("/employees/{id}")
    @RolesAllowed({"admin", "companyAdmin", "user"})
    ResponseEntity<Employee> one(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployee(id));
    }

    @PutMapping("/employees/{id}")
    @RolesAllowed({"admin", "companyAdmin"})
    ResponseEntity<Employee> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.updateEmployee(newEmployee, id));
    }


    @DeleteMapping("/employees/{id}")
    @RolesAllowed({"admin", "companyAdmin"})
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/employees/{id}/projects")
    @RolesAllowed({"admin", "companyAdmin", "user"})
    ResponseEntity<List<Project>> getAssignedProjects(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAssignedProjects(id));
    }


}