package com.api.exeptionHandling;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.api.exceptions.BadRequestException;
import com.api.exceptions.CustomNotFoundException;
import com.api.exceptions.ForbiddenException;
import com.api.exceptions.UnauthorizedException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomNotFound(CustomNotFoundException ex) {
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {
	    return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorResponse> handleUnauthorized(UnauthorizedException ex) {
	    return new ResponseEntity<>(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage()), HttpStatus.UNAUTHORIZED);
	}

    @ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<ErrorResponse> handleForbidden(ForbiddenException ex) {
	    return new ResponseEntity<>(new ErrorResponse(HttpStatus.FORBIDDEN.value(), ex.getMessage()), HttpStatus.FORBIDDEN);
    }

   
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String message = "Duplicate entry: Email already exists.";
        System.out.println("DataIntegrityViolationException: " + ex.getMessage());

        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message), HttpStatus.BAD_REQUEST);
    }


 // Inside GlobalExceptionHandler.java
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<Map<String, List<String>>> handleValidationExceptions(Exception ex) {
        Map<String, List<String>> errors = new HashMap<>();

        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException manve = (MethodArgumentNotValidException) ex;
            manve.getBindingResult().getFieldErrors().forEach(error -> {
                String fieldName = error.getField();
                String errorMessage = error.getDefaultMessage();
                errors.computeIfAbsent(fieldName, key -> new ArrayList<>()).add(errorMessage);
            });
        } 
        else if (ex instanceof HttpMessageNotReadableException) {
            HttpMessageNotReadableException hmnre = (HttpMessageNotReadableException) ex;
            Throwable mostSpecificCause = hmnre.getMostSpecificCause();

            if (mostSpecificCause instanceof InvalidFormatException) {
                InvalidFormatException ife = (InvalidFormatException) mostSpecificCause;
                String fieldName = ife.getPath().get(ife.getPath().size() - 1).getFieldName();
                
                String message;
                if (ife.getTargetType().isEnum()) {
                    Object[] enumConstants = ife.getTargetType().getEnumConstants();
                    String allowedValues = Arrays.toString(enumConstants);
                    message = "Please select the value " + ife.getValue() + " for field " + fieldName ;
                } else {
                    message = "Invalid value '" + ife.getValue() + "' for field '" + fieldName + "'";
                }

                errors.computeIfAbsent(fieldName, key -> new ArrayList<>()).add(message);
            } 
            else if (mostSpecificCause instanceof DateTimeParseException) {
                String fullMessage = mostSpecificCause.getMessage();
                String fieldName = ex.getMessage().contains("LocalTime") ? "timeOfBirth" : "dateOfBirth";
                String message = ex.getMessage().contains("LocalTime") 
                                ? "Invalid time format. Please use format HH:mm:ss" 
                                : "Invalid date format. Please use format yyyy-MM-dd";
                errors.computeIfAbsent(fieldName, key -> new ArrayList<>()).add(message);
            } 
            else {
                errors.computeIfAbsent("error", key -> new ArrayList<>()).add("Invalid request format.");
            }
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }




    // Handle General Exception (500 - Internal Server Error)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        ex.printStackTrace(); // Yeh karne se console me exact error dikhega debugging ke liye ✅

        String errorMessage = (ex.getMessage() != null && !ex.getMessage().isEmpty()) 
            ? ex.getMessage() 
            : "Internal server error occurred";

        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), 
                errorMessage
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    
}

