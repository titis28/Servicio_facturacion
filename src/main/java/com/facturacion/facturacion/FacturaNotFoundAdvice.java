package com.facturacion.facturacion;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FacturaNotFoundAdvice {
    @ExceptionHandler(FacturaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String facturaNoEncontrada(FacturaNotFoundException ex) {
    return ex.getMessage();
  }
}