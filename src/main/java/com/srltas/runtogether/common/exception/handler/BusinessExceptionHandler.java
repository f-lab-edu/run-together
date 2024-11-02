package com.srltas.runtogether.common.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.srltas.runtogether.common.exception.BusinessException;

@RestControllerAdvice
public class BusinessExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException ex) {
		return new ResponseEntity<>(ErrorResponse.of(ex.getErrorCode()), HttpStatus.BAD_REQUEST);
	}
}
