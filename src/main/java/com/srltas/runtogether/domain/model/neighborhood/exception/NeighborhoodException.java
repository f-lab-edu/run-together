package com.srltas.runtogether.domain.model.neighborhood.exception;

import com.srltas.runtogether.common.exception.BusinessException;
import com.srltas.runtogether.common.exception.code.ErrorCode;

public class NeighborhoodException extends BusinessException {

	public NeighborhoodException(ErrorCode errorCode) {
		super(errorCode);
	}
}
