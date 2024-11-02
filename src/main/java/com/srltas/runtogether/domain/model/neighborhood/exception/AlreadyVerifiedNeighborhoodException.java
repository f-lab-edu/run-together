package com.srltas.runtogether.domain.model.neighborhood.exception;

import static com.srltas.runtogether.domain.model.neighborhood.exception.code.NeighborhoodErrorCode.*;

public class AlreadyVerifiedNeighborhoodException extends NeighborhoodException {

	public AlreadyVerifiedNeighborhoodException() {
		super(ALREADY_VERIFIED_NEIGHBORHOOD);
	}
}
