package com.system.dscommerce.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.system.dscommerce.dtos.CustomError;
import com.system.dscommerce.services.exceptions.NotFoundExceptionService;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundExceptionService.class)
    public ResponseEntity<CustomError> notFound(NotFoundExceptionService e, HttpServletRequest http) {
        var status = HttpStatus.NOT_FOUND;
        var error = new CustomError(Instant.now(), status.value(), e.getMessage(), http.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
}
