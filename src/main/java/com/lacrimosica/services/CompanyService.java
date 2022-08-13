package com.lacrimosica.services;


import com.lacrimosica.keycloak.Company;
import com.lacrimosica.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CompanyService {


    static List<Company> companies = new ArrayList<>();

    @Autowired
    CompanyRepository companyRepository;

    @PostConstruct void init() {
        companyRepository.saveAll(
                Stream.of(
                        (new Company("Google")),
                        (new Company("Orbyta")),
                        (new Company("Facebook")),
                        (new Company("Microsoft")),
                        (new Company("Amazon")),
                        (new Company("IBM")),
                        (new Company("Oracle")),
                        (new Company("Apple")),
                        (new Company("Samsung")),
                        (new Company("Huawei")),
                        (new Company("Nokia")),
                        (new Company("Sony")),
                        (new Company("LG")),
                        (new Company("Motorola")))
                .collect(Collectors.toList()));
    }

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyByName(String name) {
        return companies.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    public Company getCompanyById(Long id) {
        return companies.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst().orElse(null);
    }

}
