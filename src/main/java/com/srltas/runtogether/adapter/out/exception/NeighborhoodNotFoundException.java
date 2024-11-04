package com.srltas.runtogether.adapter.out.exception;

import static com.srltas.runtogether.domain.model.neighborhood.exception.code.NeighborhoodErrorCode.*;

public class NeighborhoodNotFoundException extends EntityNotFoundException {

	public NeighborhoodNotFoundException() {
		super(NEIGHBORHOOD_NOT_FOUND);
	}
}
