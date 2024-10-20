package com.srltas.runtogether.application.port.out.dao;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record VerifyUserNeighborhoodDAO(
	long userId,
	int neighborhoodId,
	boolean verified,
	LocalDateTime verifiedAt
) {
}
