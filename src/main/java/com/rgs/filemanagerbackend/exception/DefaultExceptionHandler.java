package com.rgs.filemanagerbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
@ControllerAdvice
public class DefaultExceptionHandler{

    @ExceptionHandler(FileManagerException.class)
    protected ResponseEntity<?> exception(FileManagerException ex) {
        ExceptionDefaultMessage build = ExceptionDefaultMessage.builder()
                .messageError(ex.getMessage())
                .httpStatusCode(HttpStatus.CONFLICT.value()).build();
        return new ResponseEntity<>(build, HttpStatus.CONFLICT);
    }
}
