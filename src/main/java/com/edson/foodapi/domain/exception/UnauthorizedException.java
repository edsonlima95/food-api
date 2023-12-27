package com.edson.foodapi.domain.exception;

import org.springframework.security.access.AuthorizationServiceException;

public class UnauthorizedException extends AuthorizationServiceException {


    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
