package com.srltas.runtogether.domain.model.neighborhood.exception;

import static com.srltas.runtogether.domain.model.neighborhood.exception.code.NeighborhoodErrorCode.*;

import com.srltas.runtogether.common.exception.RunTogetherException;

public class NeighborhoodLimitExceededException extends RunTogetherException {

	public NeighborhoodLimitExceededException() {
		super(NEIGHBORHOOD_LIMIT_EXCEEDED);
	}
}
