package com.pm.patientservice.Exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleValidationExceptions() {
        // Arrange
        MethodArgumentNotValidException mockException = mock(MethodArgumentNotValidException.class);
        BindingResult mockBindingResult = mock(BindingResult.class);
        
        List<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldError("patient", "email", "Email is required"));
        
        when(mockException.getBindingResult()).thenReturn(mockBindingResult);
        when(mockBindingResult.getFieldErrors()).thenReturn(fieldErrors);
        
        // Act
        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleValidationExceptions(mockException);
        
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Email is required", response.getBody().get("email"));
        
        // The logging is tested implicitly as any errors would appear in the console
        System.out.println("[DEBUG_LOG] Validation exception handler test completed");
    }

    @Test
    void testHandleEmailAlreadyExistsException() {
        // Arrange
        EmailAlreadyExistsException exception = new EmailAlreadyExistsException("test@example.com already exists");
        
        // Act
        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleEmailAlreadyExistsException(exception);
        
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Email already exists", response.getBody().get("message"));
        
        // The logging is tested implicitly as any errors would appear in the console
        System.out.println("[DEBUG_LOG] Email already exists exception handler test completed");
    }

    @Test
    void testHandleGenericException() {
        // Arrange
        Exception exception = new RuntimeException("Unexpected error");
        
        // Act
        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleGenericException(exception);
        
        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("An unexpected error occurred", response.getBody().get("message"));
        
        // The logging is tested implicitly as any errors would appear in the console
        System.out.println("[DEBUG_LOG] Generic exception handler test completed");
    }
}