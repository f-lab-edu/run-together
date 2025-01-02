package com.srltas.runtogether.domain.model.group;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class Group {

	private final String id;
	private final String name;
	private final String description;
	private final String neighborhoodId;
	private final String createByUserId;
	private final LocalDateTime createdAt;

	private final Set<String> groupMemberIds;
}
