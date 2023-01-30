package com.timeks.base.handler;

import com.timeks.base.exception.TimeksRuntimeException;
import com.timeks.base.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ExceptionHandlerExceptionResolver {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        List<String> fieldErrors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            fieldErrors.add(errorMessage);
        });
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("Following fields are not compatible")
                .fieldErrors(fieldErrors)
                .build();
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(TimeksRuntimeException.class)
    public ResponseEntity<ErrorResponse> handleTimeksExceptions(
            TimeksRuntimeException ex) {
        var errorResponse = ErrorResponse.builder()
                .error(ex.getMessage())
                .build();
        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        var errorResponse = ErrorResponse.builder()
                .error("Looks like an error occured that we do not expect")
                .build();
        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }

}
