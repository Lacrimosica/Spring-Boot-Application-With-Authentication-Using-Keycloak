package com.lacrimosica.advices;

import com.lacrimosica.exceptions.CompanyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CompanyNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CompanyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String companyNotFoundHandler(CompanyNotFoundException ex) {
        return ex.getMessage();
    }

}
