package com.komuna.komuna_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException; // Import añadido

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        Map<String, String> body = new HashMap<>();
        body.put("message", ex.getMessage());
        System.err.println("ResourceNotFoundException (personalizada) capturada: " + ex.getMessage());
        ex.printStackTrace(); // Loggear la traza para depuración
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<?> duplicateResourceException(DuplicateResourceException ex, WebRequest request) {
        Map<String, String> body = new HashMap<>();
        body.put("message", ex.getMessage());
        System.err.println("DuplicateResourceException capturada: " + ex.getMessage());
        ex.printStackTrace(); // Loggear la traza para depuración
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> invalidCredentialsException(InvalidCredentialsException ex, WebRequest request) {
        Map<String, String> body = new HashMap<>();
        body.put("message", ex.getMessage());
        System.err.println("InvalidCredentialsException capturada: " + ex.getMessage());
        ex.printStackTrace(); // Loggear la traza para depuración
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        System.err.println("MethodArgumentNotValidException capturada: " + errors.toString());
        // ex.printStackTrace(); // Opcional, usualmente los mensajes de campo son suficientes
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // --- MANEJADOR ESPECÍFICO AÑADIDO PARA NoResourceFoundException DE SPRING ---
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoResourceFoundException(NoResourceFoundException ex, WebRequest request) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Recurso API No Encontrado");
        body.put("message", "El endpoint solicitado '" + ex.getResourcePath() + "' (método: " + ex.getHttpMethod() + ") no pudo ser encontrado en el servidor.");
        body.put("path", ex.getResourcePath());
        System.err.println("LOG DESDE GLOBAL EXCEPTION HANDLER: NoResourceFoundException de Spring capturada para el recurso: " + ex.getResourcePath() + " con método HTTP: " + ex.getHttpMethod());
        // ex.printStackTrace(); // Opcional: para ver la traza completa en consola
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> globalExceptionHandler(Exception ex, WebRequest request) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Error Interno del Servidor");
        body.put("message", "Ocurrió un error inesperado en el servidor. Por favor, contacte al administrador.");
        System.err.println("LOG DESDE GLOBAL EXCEPTION HANDLER: Excepción genérica no capturada específicamente:");
        ex.printStackTrace(); // Muy importante para depurar errores 500
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}