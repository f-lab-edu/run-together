package com.srltas.runtogether.domain.model.neighborhood.exception;

import static com.srltas.runtogether.domain.model.neighborhood.exception.code.NeighborhoodErrorCode.*;

import com.srltas.runtogether.common.exception.RunTogetherException;

public class NeighborhoodNotRegisteredException extends RunTogetherException {

	public NeighborhoodNotRegisteredException() {
		super(NEIGHBORHOOD_NOT_REGISTERED);
	}
}
