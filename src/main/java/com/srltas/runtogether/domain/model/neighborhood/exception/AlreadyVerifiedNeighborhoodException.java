package com.srltas.runtogether.domain.model.neighborhood.exception;

import static com.srltas.runtogether.domain.model.neighborhood.exception.code.NeighborhoodErrorCode.*;

import com.srltas.runtogether.common.exception.RunTogetherException;

public class AlreadyVerifiedNeighborhoodException extends RunTogetherException {

	public AlreadyVerifiedNeighborhoodException() {
		super(ALREADY_VERIFIED_NEIGHBORHOOD);
	}
}
