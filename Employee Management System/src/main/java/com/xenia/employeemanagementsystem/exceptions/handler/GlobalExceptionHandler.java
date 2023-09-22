package com.xenia.employeemanagementsystem.exceptions.handler;

import com.xenia.employeemanagementsystem.configuration.ApplicationContextProvider;
import com.xenia.employeemanagementsystem.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException exception){
        return new ResponseEntity<>(exception.getErrorMessage(), exception.HTTP_STATUS);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException exception){
        String className = exception.getConstraintViolations().iterator().next().getRootBeanClass().getName();

        boolean isSpringBean = ApplicationContextProvider.getApplicationContext().containsBeanDefinition(className);

        HttpStatus status = isSpringBean ? HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR;

        return new ResponseEntity<>(exception.getMessage(), status);
    }
}
