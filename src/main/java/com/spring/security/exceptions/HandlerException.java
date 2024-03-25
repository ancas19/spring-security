package com.spring.security.exceptions;

import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public final ProblemDetail handlerBadRequestException(BadRequestException badRequest){
        ProblemDetail body=ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,"BAD REQUEST");
        body.setDetail(badRequest.getMessage());
        return body;
    }

    @ExceptionHandler(NotFoundException.class)
    public final ProblemDetail handlerNotFoundException(NotFoundException notFoundException){
        ProblemDetail body=ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,"NOT FOUND");
        body.setDetail(notFoundException.getMessage());
        return body;
    }
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> errors= new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
            String fieldName= ((FieldError) error).getField();
            String message=error.getDefaultMessage();
            errors.put(fieldName,message);
        });
        return new ResponseEntity<Object>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    public final ProblemDetail handlerForbiddenException(ForbiddenException forbiddenException){
        ProblemDetail body=ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED,"UNAUTHORIZED");
        body.setDetail(forbiddenException.getMessage());
        return body;
    }

    @ExceptionHandler(Exception.class)
    public final ProblemDetail handlerException(Exception exception){
        ProblemDetail body=ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL SERVER ERROR");
        body.setDetail(exception.getMessage());
        return body;
    }
}
