package com.flpbrrs.devdeve.api.infra;

import com.flpbrrs.devdeve.domain.exceptions.DomainException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DomainExceptionHandler {
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<String> handleDomainExceptions(DomainException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
