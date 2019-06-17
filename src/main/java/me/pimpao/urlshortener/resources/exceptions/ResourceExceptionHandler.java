package me.pimpao.urlshortener.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import me.pimpao.urlshortener.services.exceptions.ExpiredUrlException;
import me.pimpao.urlshortener.services.exceptions.InvalidUrlException;
import me.pimpao.urlshortener.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Not found", e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(ExpiredUrlException.class)
	public ResponseEntity<StandardError> expiredUrl(ExpiredUrlException e) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.GONE.value(), "Expired Url", e.getMessage());
		return ResponseEntity.status(HttpStatus.GONE).body(err);
	}
	
	@ExceptionHandler(InvalidUrlException.class)
	public ResponseEntity<StandardError> invalidUrl(InvalidUrlException e) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Invalid Url", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}
