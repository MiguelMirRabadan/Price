package com.InditexEnterprice.price.infraestructura.controllers;

import lombok.extern.log4j.Log4j2;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
@Log4j2
public class PriceControllerAdvice {

    @ExceptionHandler( EmptyResultDataAccessException.class)
    protected ResponseEntity<Object> handleAccessConflict(EmptyResultDataAccessException ex) {
        log.error("An ERROR Message");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empty result on data access: "+ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleBadRequest(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("Error en los argumentos:\n");
        errorMessage.append(fieldErrors.stream().map(FieldError::getField).toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(IllegalAccessException.class)
    protected ResponseEntity<String> handleIllegalAccessException(IllegalAccessException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access not supported: "+ex.getMessage());
    }

    @ExceptionHandler(JdbcSQLIntegrityConstraintViolationException.class)
    protected ResponseEntity<String> handleSQLErrorException(JdbcSQLIntegrityConstraintViolationException ex) {
        log.error("An ERROR Message");
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al especificar las restricciones de PK o FK: "+ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<String> handleSQLErrorException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body("Error al especificar un argumento: "+ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<String> handleSQLErrorException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error de integridad de datos: "+ex.getMessage());
    }



}
