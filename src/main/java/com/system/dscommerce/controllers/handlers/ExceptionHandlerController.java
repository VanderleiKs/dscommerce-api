package com.system.dscommerce.controllers.handlers;

import java.time.Instant;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.system.dscommerce.dtos.CustomError;
import com.system.dscommerce.dtos.FieldMessage;
import com.system.dscommerce.services.exceptions.DatabaseExceptionService;
import com.system.dscommerce.services.exceptions.NotFoundExceptionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundExceptionService.class)
    public ResponseEntity<CustomError> notFound(NotFoundExceptionService e, HttpServletRequest http) {
        var status = HttpStatus.NOT_FOUND;
        var error = new CustomError(Instant.now(), status.value(), e.getMessage(), http.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DatabaseExceptionService.class)
    public ResponseEntity<CustomError> database(DatabaseExceptionService e, HttpServletRequest http) {
        var status = HttpStatus.BAD_REQUEST;
        var error = new CustomError(Instant.now(), status.value(), e.getMessage(), http.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomError> validation(ConstraintViolationException e, HttpServletRequest http) {
        var status = HttpStatus.UNPROCESSABLE_ENTITY;
        var fieldMessages = new ArrayList<FieldMessage>();
        for (ConstraintViolation<?> err : e.getConstraintViolations()) {
            fieldMessages.add(new FieldMessage(err.getPropertyPath().toString(), err.getMessage()));
        }
        var error = new CustomError(Instant.now(), status.value(), "Campos inválidos",
                http.getRequestURI(),
                fieldMessages);

        return ResponseEntity.status(status).body(error);
    }
}
