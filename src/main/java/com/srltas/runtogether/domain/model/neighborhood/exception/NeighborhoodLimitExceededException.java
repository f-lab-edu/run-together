package com.srltas.runtogether.domain.model.neighborhood.exception;

import static com.srltas.runtogether.domain.model.neighborhood.exception.code.NeighborhoodErrorCode.*;

public class NeighborhoodLimitExceededException extends NeighborhoodException {

	public NeighborhoodLimitExceededException() {
		super(NEIGHBORHOOD_LIMIT_EXCEEDED);
	}
}
