package com.example.springex.error;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiBaseException.class)
    public ResponseEntity<ErrorMessages> handleApiExceptions(ApiBaseException ex, WebRequest request) {
        ErrorMessages errorMessages = new ErrorMessages(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorMessages, ex.getStatusCode());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ValidationError validationError = new ValidationError();
        validationError.setUri(request.getDescription(false));

        List<FieldError> fieldError = ex.getBindingResult().getFieldErrors();
        for (FieldError f:fieldError){
            validationError.addErrors(f.getDefaultMessage());
        }
        return new ResponseEntity<>(validationError ,HttpStatus.BAD_REQUEST);
    }
}
