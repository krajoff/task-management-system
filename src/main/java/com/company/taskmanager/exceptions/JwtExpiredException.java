package com.company.taskmanager.exceptions;

import io.jsonwebtoken.ExpiredJwtException;

public class JwtExpiredException extends RuntimeException {
    public JwtExpiredException(String message) {
        super(message);
    }
}
