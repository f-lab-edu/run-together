package com.srltas.runtogether.adapter.out.persistence.mybatis.dto;

import lombok.Builder;

@Builder
public record DeleteGroupDTO(
	String groupId
) {
}
