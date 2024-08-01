package com.dev.encurta_ai.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerExcpetion {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(NotFoundException e) {
        ErrorResponse erro = new ErrorResponse(HttpStatus.NOT_FOUND, e.getMsg());
        return new ResponseEntity(erro, HttpStatus.NOT_FOUND);
    }
}
