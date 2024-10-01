package com.srltas.runtogether.application.port.in;

import lombok.Builder;

@Builder
public record NeighborhoodVerificationCommand(
	double latitude,
	double longitude,
	int neighborhoodId
) {
}
