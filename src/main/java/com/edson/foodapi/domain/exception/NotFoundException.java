package com.edson.foodapi.domain.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        this("Recurso não encontrado");
    }

    public NotFoundException(Long id) {
        this(String.format("Recurso de ID %d, não pode ser econtrado",id));
    }
}

