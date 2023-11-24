package com.edson.foodapi.domain.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Long id) {
        this(String.format("O recurso de ID %d, não pode ser econtrado",id));
    }
}

