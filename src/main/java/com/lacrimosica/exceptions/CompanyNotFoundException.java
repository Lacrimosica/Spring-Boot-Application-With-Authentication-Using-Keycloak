package com.lacrimosica.exceptions;

public class CompanyNotFoundException extends RuntimeException{

        public CompanyNotFoundException(Long id) {
            super("could not find company" + id);
        }
}
