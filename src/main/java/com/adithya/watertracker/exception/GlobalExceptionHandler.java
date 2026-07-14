package com.adithya.watertracker.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.adithya.watertracker.dto.response.ApiResponse;

import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ApiResponse> handleUserExists(UserAlreadyExistsException ex) {

	    ApiResponse response = new ApiResponse(false, ex.getMessage());

	    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ApiResponse<?>> handleInvalidCredentials(
	        InvalidCredentialsException ex) {

	    ApiResponse<?> response =
	            new ApiResponse<>(false, ex.getMessage());

	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	            .body(response);
	}
	
	@ExceptionHandler(WaterAlreadyExistsException.class)
	public ResponseEntity<ApiResponse<?>> handleWaterAlreadyExists(
	        WaterAlreadyExistsException ex) {

	    ApiResponse<?> response =
	            new ApiResponse<>(false, ex.getMessage());

	    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	            .body(response);
	}
    
	@ExceptionHandler(WaterEntryNotFoundException.class)
	public ResponseEntity<ApiResponse<?>> handleWaterEntryNotFound(
	        WaterEntryNotFoundException ex) {

	    return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            .body(new ApiResponse<>(
	                    false,
	                    ex.getMessage()
	            ));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<?>> handleValidationException(
	        MethodArgumentNotValidException ex) {

	    String message = ex.getBindingResult()
	            .getFieldErrors()
	            .stream()
	            .map(error -> error.getDefaultMessage())
	            .collect(Collectors.joining(", "));

	    return ResponseEntity.badRequest()
	            .body(new ApiResponse<>(
	                    false,
	                    message
	            ));
	}

}