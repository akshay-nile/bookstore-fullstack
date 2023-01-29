package com.stackroute.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	class ExceptionTemplate {

		private Date timestamp;
		private String message;

		public ExceptionTemplate(String message) {
			this.timestamp = new Date();
			this.message = message;
		}

		public Date getTimestamp() {
			return timestamp;
		}

		public String getMessage() {
			return message;
		}
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ExceptionTemplate> handleRuntimeExceptions(RuntimeException e) {
		ExceptionTemplate temp = new ExceptionTemplate(e.getMessage());
		return new ResponseEntity<>(temp, HttpStatus.BAD_REQUEST);
	}
}
