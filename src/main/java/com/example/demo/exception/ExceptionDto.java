package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public record ExceptionDto(HttpStatus statusName, String msgError) {

}
