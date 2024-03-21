package com.flpbrrs.devdeve.api.infra;

import com.flpbrrs.devdeve.domain.exceptions.DomainException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.stream.Collectors;

@RestControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request
    ) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("One or more fields are invalid");
        problemDetail.setType(URI.create("https://flpbrrs.com/errors/invalid-fields"));

        var fields = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .collect(
                        Collectors.toMap(
                                error -> ((FieldError) error).getField(),
                                error -> messageSource.getMessage(error, LocaleContextHolder.getLocale())
                        )
                );

        problemDetail.setProperty("fields", fields);

        return super.handleExceptionInternal(ex, problemDetail, headers, status, request);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<String> handleDomainExceptions(DomainException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
