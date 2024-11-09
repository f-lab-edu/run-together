package com.srltas.runtogether.adapter;

import static com.srltas.runtogether.common.exception.code.CommonErrorCode.*;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.srltas.runtogether.adapter.in.exception.UnauthorizedException;
import com.srltas.runtogether.application.exception.EntityNotFoundException;
import com.srltas.runtogether.common.exception.ErrorResponse;
import com.srltas.runtogether.common.exception.RunTogetherException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
		return new ResponseEntity<>(ErrorResponse.of(ex.getErrorCode()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UnauthorizedException.class)
	protected ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex) {
		return new ResponseEntity<>(ErrorResponse.of(ex.getErrorCode()), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(RunTogetherException.class)
	protected ResponseEntity<Object> handleRunTogetherException(RunTogetherException ex) {
		return new ResponseEntity<>(ErrorResponse.of(ex.getErrorCode()), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
		HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return new ResponseEntity<>(ErrorResponse.of(METHOD_NOT_ALLOWED), HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
		HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return new ResponseEntity<>(ErrorResponse.of(UNSUPPORTED_MEDIA_TYPE), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
		HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return new ResponseEntity<>(ErrorResponse.of(NOT_ACCEPTABLE), HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(
		MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return new ResponseEntity<>(ErrorResponse.of(MISSING_PATH_VARIABLE), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
		MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return new ResponseEntity<>(ErrorResponse.of(MISSING_PARAMETER), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(
		ServletRequestBindingException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return new ResponseEntity<>(ErrorResponse.of(BINDING_ERROR), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return new ResponseEntity<>(ErrorResponse.of(INVALID_REQUEST_BODY), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(
		TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return new ResponseEntity<>(ErrorResponse.of(TYPE_MISMATCH), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
		HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return new ResponseEntity<>(ErrorResponse.of(INVALID_REQUEST_BODY), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleInternalServerException(Exception ex) {
		return new ResponseEntity<>(ErrorResponse.of(SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
