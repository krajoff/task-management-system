package com.company.taskmanager.exceptions;

import com.company.taskmanager.models.errors.AppError;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<AppError> catchResourceNotFoundException
            (ResourceNotFoundException e) {
        return new ResponseEntity<>(
                new AppError(HttpStatus.NOT_FOUND.value(),
                        e.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<AppError> catchAuthException
            (AuthException e) {
        return new ResponseEntity<>(
                new AppError(HttpStatus.UNAUTHORIZED.value(),
                        e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> catchJwtAuthException
            (JwtAuthException e) {
        return new ResponseEntity<>(
                new AppError(HttpStatus.UNAUTHORIZED.value(),
                        e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> catchJwtExpiredException
            (JwtExpiredException e) {
        return new ResponseEntity<>(
                new AppError(HttpStatus.UNAUTHORIZED.value(),
                        e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> catchUserNotFoundException
            (UsernameNotFoundException e) {
        return new ResponseEntity<>(
                new AppError(HttpStatus.NOT_FOUND.value(),
                        e.getMessage()), HttpStatus.NOT_FOUND);
    }


}
