package com.club.fitness.customer.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.club.fitness.customer.controller.dto.output.ErrorDto;
import com.club.fitness.customer.exception.NotFoundException;
import com.club.fitness.customer.exception.ValidationException;

@ControllerAdvice
public class BaseExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorDto> handleNotFoundException(final NotFoundException e) {
		logger.error(e.getMessage(), e);

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
							 .body(new ErrorDto(HttpStatus.NOT_FOUND.value(),
									 			HttpStatus.NOT_FOUND.getReasonPhrase(),
									 			e.getMessage()));
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ErrorDto> handleValidationException(final ValidationException e) {
		logger.error(e.getMessage(), e);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							 .body(new ErrorDto(HttpStatus.BAD_REQUEST.value(),
									 		    HttpStatus.BAD_REQUEST.getReasonPhrase(),
									 		    e.getMessage()));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorDto> handleCommonRuntimeException(final RuntimeException e) {
		logger.error(e.getMessage(), e);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							 .body(new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),
									 			HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
									 			e.getMessage()));
	}
}
