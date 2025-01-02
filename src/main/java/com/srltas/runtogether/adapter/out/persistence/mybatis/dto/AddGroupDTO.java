package com.srltas.runtogether.adapter.out.persistence.mybatis.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record AddGroupDTO(
	String groupId,
	String groupName,
	String description,
	String neighborhoodId,
	String createByUserId,
	LocalDateTime createAt
) {
}
