package com.example.springboot.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ControllerAdvice
public class ProductExceptionController {
	@ResponseBody
	@ExceptionHandler(ProductNotfoundException.class)
	public ResponseEntity<Object> exception(ProductNotfoundException exception) {
		return new ResponseEntity<>("Product not found on the list", HttpStatus.NOT_FOUND);
	}
}