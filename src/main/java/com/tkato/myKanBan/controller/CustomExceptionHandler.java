package com.tkato.myKanBan.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tkato.myKanBan.exception.ProjectNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
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