package com.lacrimosica.controllers;

import com.lacrimosica.keycloak.Company;
import com.lacrimosica.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class CompanyController {


    @Autowired
    CompanyService companyService;

    @GetMapping("/companies")
    public List<Company> getCompanies() {
        return companyService.getCompanies();
    }

    @GetMapping("/companies/{name}")
    public Company getCompanyByName(@PathVariable String name) {
        return companyService.getCompanyByName(name);
    }

    @GetMapping("/companies/{id}")
    public Company getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }


}
