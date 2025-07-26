package com.bruno.moviecatalog.controller.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.bruno.moviecatalog.exceptions.DuplicateEntityException;
import com.bruno.moviecatalog.exceptions.ResourceNotFoundException;
import com.bruno.moviecatalog.exceptions.UnregisteredEntityException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {
		Map<String, Object> body = new HashMap<String, Object>();

		body.put("timestamp", new Date());
		body.put("path", request.getDescription(false));
		
		Map<String,String> errors = new HashMap<String, String>();
		
		e.getBindingResult().getAllErrors().forEach((err) -> {
			errors.put(((FieldError)err).getField(), err.getDefaultMessage());
		});
		body.put("message", errors);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handlerMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, WebRequest request) {
		Map<String, Object> body = new HashMap<String, Object>();
		
		body.put("timestamp", new Date());
		body.put("path", request.getDescription(false));
		body.put("message", e.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	@ExceptionHandler(DuplicateEntityException.class)
	public ResponseEntity<?> handlerDuplicateEntityException(DuplicateEntityException e, WebRequest request) {
		Map<String, Object> body = new HashMap<String, Object>();
		
		body.put("timestamp", new Date());
		body.put("path", request.getDescription(false));
		body.put("message", e.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handlerHttpMessageNotReadableException(HttpMessageNotReadableException e, WebRequest request) {
		Map<String, Object> body = new HashMap<String, Object>();
		
		body.put("timestamp", new Date());
		body.put("path", request.getDescription(false));
		body.put("message", e.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	@ExceptionHandler(UnregisteredEntityException.class)
	public ResponseEntity<?> handler(UnregisteredEntityException e, WebRequest request) {
		Map<String, Object> body = new HashMap<String, Object>();
		
		body.put("timestamp", new Date());
		body.put("path", request.getDescription(false));
		body.put("message", e.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
		Map<String, Object> body = new HashMap<String, Object>();
		
		body.put("timestam", new Date());
		body.put("path", request.getDescription(false));
		body.put("message", e.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handler(RuntimeException e, WebRequest request) {
		Map<String, Object> body = new HashMap<String, Object>();
		
		body.put("timestam", new Date());
		body.put("path", request.getDescription(false));
		body.put("message", e.getMessage());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
	}

}
