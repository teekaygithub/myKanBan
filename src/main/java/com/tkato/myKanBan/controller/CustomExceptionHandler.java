package com.tkato.myKanBan.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tkato.myKanBan.exception.ProjectAlreadyExists;
import com.tkato.myKanBan.exception.ProjectNotFoundException;
import com.tkato.myKanBan.exception.TicketNotFoundException;
import com.tkato.myKanBan.exception.UserAlreadyExistsException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    // General
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
            Map<String, String> errors = new HashMap<>();

            ex.getBindingResult().getAllErrors().forEach((err) -> {
                String fieldName = ((FieldError)err).getField();
                String message = err.getDefaultMessage();
                errors.put(fieldName, message);
            });

            return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }
    
    // User exceptions
    @ExceptionHandler(UsernameNotFoundException.class)
    public void handleUsernameNotFoundException(UsernameNotFoundException ex, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Map<String, String> errors = new HashMap<>();
        errors.put("error_message", ex.getMessage());
        new ObjectMapper().writeValue(response.getOutputStream(), errors);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public void handleUserAlreadyExistsException(UserAlreadyExistsException ex, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Map<String, String> errors = new HashMap<>();
        errors.put("error_message", ex.getMessage());
        new ObjectMapper().writeValue(response.getOutputStream(), errors);
    }

    // Project exceptions
    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstraintViolation(ConstraintViolationException ex, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Map<String, String> errors = new HashMap<>();
        errors.put("error_message", ex.getMessage());
        new ObjectMapper().writeValue(response.getOutputStream(), errors);
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    public void handleProjectNotFoundException(ProjectNotFoundException ex, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Map<String, String> errors = new HashMap<>();
        errors.put("error_message", ex.getMessage());
        new ObjectMapper().writeValue(response.getOutputStream(), errors);
    }

    @ExceptionHandler(ProjectAlreadyExists.class)
    public void handleProjectAlreadyExistsException(ProjectAlreadyExists ex, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Map<String, String> errors = new HashMap<>();
        errors.put("error_message", ex.getMessage());
        new ObjectMapper().writeValue(response.getOutputStream(), errors);
    }

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<String> handle(TicketNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Requested ticket does not exist");
    }

    // JSON web token
    // TODO: respond with JSON with more clear explanation of failure
    @ExceptionHandler(JWTCreationException.class)
    public void handleJwtCreationException(JWTCreationException ex, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Map<String, String> errors = new HashMap<>();
        errors.put("error_message", ex.getMessage());
        new ObjectMapper().writeValue(response.getOutputStream(), errors);
    }

    // TODO: respond with JSON with more clear explanation of failure
    @ExceptionHandler(JWTVerificationException.class)
    public void handleJwtVerificationException(JWTVerificationException ex, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Map<String, String> errors = new HashMap<>();
        errors.put("error_message", ex.getMessage());
        new ObjectMapper().writeValue(response.getOutputStream(), errors);
    }

}