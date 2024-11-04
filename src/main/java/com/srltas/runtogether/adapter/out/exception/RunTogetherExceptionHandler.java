package com.srltas.runtogether.adapter.out.exception;

import static com.srltas.runtogether.common.exception.code.CommonErrorCode.METHOD_NOT_ALLOWED;
import static com.srltas.runtogether.common.exception.code.CommonErrorCode.UNSUPPORTED_MEDIA_TYPE;
import static com.srltas.runtogether.common.exception.code.CommonErrorCode.*;
import static org.springframework.http.HttpStatus.*;

import java.net.BindException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.srltas.runtogether.application.exception.UnauthorizedException;
import com.srltas.runtogether.common.exception.ErrorResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class RunTogetherExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEntityNotFoundException(final EntityNotFoundException ex) {
		return new ResponseEntity<>(ErrorResponse.of(ex.getErrorCode()), NOT_FOUND);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorResponse> handleUnauthorizedException(final UnauthorizedException ex) {
		return new ResponseEntity<>(ErrorResponse.of(UNAUTHORIZED_REQUEST), FORBIDDEN);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
		final HttpMessageNotReadableException ex) {
		return new ResponseEntity<>(ErrorResponse.of(INVALID_REQUEST_BODY), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
		final HttpRequestMethodNotSupportedException ex) {
		return new ResponseEntity<>(ErrorResponse.of(METHOD_NOT_ALLOWED), HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException(
		final HttpMediaTypeNotSupportedException ex) {
		return new ResponseEntity<>(ErrorResponse.of(UNSUPPORTED_MEDIA_TYPE), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(
		final MissingServletRequestParameterException ex) {
		return new ResponseEntity<>(ErrorResponse.of(MISSING_PARAMETER), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleTypeMismatchException(final TypeMismatchException ex) {
		return new ResponseEntity<>(ErrorResponse.of(TYPE_MISMATCH), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BindException.class)
	public ResponseEntity<ErrorResponse> handleBindException(final BindException ex) {
		return new ResponseEntity<>(ErrorResponse.of(BINDING_ERROR), BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolationException(final ConstraintViolationException ex) {
		return new ResponseEntity<>(ErrorResponse.of(CONSTRAINT_VIOLATION), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(final NoHandlerFoundException ex) {
		return new ResponseEntity<>(ErrorResponse.of(NO_HANDLER_FOUND), HttpStatus.NOT_FOUND);
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
