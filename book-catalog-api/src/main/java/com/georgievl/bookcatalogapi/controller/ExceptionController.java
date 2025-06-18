package com.georgievl.bookcatalogapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
public class ExceptionController {
//    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Void> handleNotFoundException(){
        return ResponseEntity.notFound().build();
    }
}
