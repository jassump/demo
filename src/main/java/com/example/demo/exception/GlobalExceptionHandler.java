package com.example.demo.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	

	    @ExceptionHandler(BusinessException.class)
	    public ResponseEntity<ExceptionDto> DataIntegrityViolationException(Exception e){

	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
	            new ExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage())
	        );
	    }
	    
	    
	    @ExceptionHandler(NoResourceFoundException.class)
	    public ResponseEntity<ExceptionDto> noResourceFoundException(Exception e){

	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
	            new ExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage())
	        );
	    }
}