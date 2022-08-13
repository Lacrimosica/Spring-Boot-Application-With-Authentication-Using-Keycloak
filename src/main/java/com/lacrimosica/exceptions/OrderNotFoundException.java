package com.lacrimosica.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Could not find Order with id: " + id);
    }

    public OrderNotFoundException(String name) {
        super("Could not find Order named: " + name);
    }
}
