package com.srltas.runtogether.application.port.out;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record VerifyUserNeighborhoodCommand(
	long userId,
	int neighborhoodId,
	boolean verified,
	LocalDateTime verifiedAt
) {
}
