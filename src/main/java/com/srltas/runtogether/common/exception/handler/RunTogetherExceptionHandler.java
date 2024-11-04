package com.srltas.runtogether.common.exception.handler;

import static com.srltas.runtogether.common.exception.code.CommonErrorCode.*;
import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.srltas.runtogether.common.exception.BusinessException;
import com.srltas.runtogether.adapter.out.exception.EntityNotFoundException;
import com.srltas.runtogether.common.exception.UnauthorizedException;

@RestControllerAdvice
public class RunTogetherExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException ex) {
		return new ResponseEntity<>(ErrorResponse.of(ex.getErrorCode()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEntityNotFoundException(final EntityNotFoundException ex) {
		return new ResponseEntity<>(ErrorResponse.of(ex.getErrorCode()), NOT_FOUND);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex) {
		return new ResponseEntity<>(ErrorResponse.of(UNAUTHORIZED_REQUEST), FORBIDDEN);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoResourceFoundException(final NoResourceFoundException ex) {
		return new ResponseEntity<>(ErrorResponse.of(NO_RESOURCE_FOUND), NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
		return new ResponseEntity<>(ErrorResponse.of(INVALID_REQUEST_PARAMETER), BAD_REQUEST);
	}

	@ExceptionHandler(HttpMediaTypeException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(final HttpMediaTypeException ex) {
		return new ResponseEntity<>(ErrorResponse.of(INVALID_HEADER_REQUEST), BAD_REQUEST);
	}

	@ExceptionHandler(MissingRequestValueException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(final MissingRequestValueException ex) {
		return new ResponseEntity<>(ErrorResponse.of(INVALID_HEADER_REQUEST), BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleInternalServerError(final Exception ex) {
		return new ResponseEntity<>(ErrorResponse.of(SERVER_ERROR), INTERNAL_SERVER_ERROR);
	}
}
