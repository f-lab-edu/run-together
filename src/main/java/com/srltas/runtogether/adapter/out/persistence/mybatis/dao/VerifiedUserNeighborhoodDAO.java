package com.srltas.runtogether.adapter.out.persistence.mybatis.dao;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record VerifiedUserNeighborhoodDAO(
	String userId,
	String neighborhoodId,
	boolean verified,
	LocalDateTime verifiedAt
) {
}
