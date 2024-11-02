package com.srltas.runtogether.application.port.out;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record VerifyUserNeighborhoodCommand(
	String userId,
	String neighborhoodId,
	boolean verified,
	LocalDateTime verifiedAt
) {
}
