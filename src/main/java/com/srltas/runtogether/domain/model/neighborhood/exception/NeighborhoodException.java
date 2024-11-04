package com.srltas.runtogether.domain.model.neighborhood.exception;

import com.srltas.runtogether.common.exception.RunTogetherException;
import com.srltas.runtogether.common.exception.code.ErrorCode;

public class NeighborhoodException extends RunTogetherException {

	public NeighborhoodException(ErrorCode errorCode) {
		super(errorCode);
	}
}
