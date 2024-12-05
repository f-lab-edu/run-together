package com.srltas.runtogether.adapter.out.persistence.mybatis.dto;

import lombok.Builder;

@Builder
public record DeleteUserNeighborhoodDTO(
	String userId,
	String neighborhoodId
) {
}
