package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecursoNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5747165165353862040L;
	public RecursoNotFoundException() {
        super();
    }
    public RecursoNotFoundException(String message) {
        super(message);
    }
}