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
import com.srltas.runtogether.common.exception.code.ErrorCode;
import com.srltas.runtogether.common.log.RunTogetherMDC;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
		logAccess(ex.getErrorCode());
		return new ResponseEntity<>(ErrorResponse.of(ex.getErrorCode()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UnauthorizedException.class)
	protected ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex) {
		logAccess(ex.getErrorCode());
		return new ResponseEntity<>(ErrorResponse.of(ex.getErrorCode()), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(RunTogetherException.class)
	protected ResponseEntity<Object> handleRunTogetherException(RunTogetherException ex) {
		logAccess(ex.getErrorCode());
		return new ResponseEntity<>(ErrorResponse.of(ex.getErrorCode()), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
		HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		logAccess(METHOD_NOT_ALLOWED);
		return new ResponseEntity<>(ErrorResponse.of(METHOD_NOT_ALLOWED), HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
		HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		logAccess(UNSUPPORTED_MEDIA_TYPE);
		return new ResponseEntity<>(ErrorResponse.of(UNSUPPORTED_MEDIA_TYPE), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
		HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		logAccess(NOT_ACCEPTABLE);
		return new ResponseEntity<>(ErrorResponse.of(NOT_ACCEPTABLE), HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(
		MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		logAccess(MISSING_PATH_VARIABLE);
		return new ResponseEntity<>(ErrorResponse.of(MISSING_PATH_VARIABLE), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
		MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		logAccess(MISSING_PARAMETER);
		return new ResponseEntity<>(ErrorResponse.of(MISSING_PARAMETER), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(
		ServletRequestBindingException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		logAccess(BINDING_ERROR);
		return new ResponseEntity<>(ErrorResponse.of(BINDING_ERROR), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		logAccess(INVALID_REQUEST_BODY);
		return new ResponseEntity<>(ErrorResponse.of(INVALID_REQUEST_BODY), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(
		TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		logAccess(TYPE_MISMATCH);
		return new ResponseEntity<>(ErrorResponse.of(TYPE_MISMATCH), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
		HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		logAccess(INVALID_REQUEST_BODY);
		return new ResponseEntity<>(ErrorResponse.of(INVALID_REQUEST_BODY), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleInternalServerException(Exception ex) {
		logError(SERVER_ERROR, ex);
		return new ResponseEntity<>(ErrorResponse.of(SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private void logAccess(ErrorCode errorCode) {
		RunTogetherMDC.put("errorCode", String.valueOf(errorCode.getCode()));
	}

	private void logError(ErrorCode errorCode, Exception ex) {
		RunTogetherMDC.put("errorCode", String.valueOf(errorCode.getCode()));
		RunTogetherMDC.put("errorName", ex.getClass().getSimpleName());
		log.error(ex.getMessage());
	}
}
