package ru.abenefic.spring.shop.core.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<?> handleNoSuchPageException(NoSuchPageException e) {
        ShopError err = new ShopError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ShopError err = new ShopError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
        ShopError err = new ShopError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        ShopError err = new ShopError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> handleInvalidTokenException(InvalidTokenException e) {
        ShopError err = new ShopError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
    }
}
