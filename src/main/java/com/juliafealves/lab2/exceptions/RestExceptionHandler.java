package com.juliafealves.lab2.exceptions;

import com.juliafealves.lab2.exceptions.product.ProductNotFoundException;
import com.juliafealves.lab2.rest.models.Product;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomRestError> handleAnyException(Exception exception, WebRequest request) {
        CustomRestError customRestError = new CustomRestError(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(customRestError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<CustomRestError> notFound(Exception exception, WebRequest request) {
        CustomRestError customRestError = new CustomRestError(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(customRestError, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
