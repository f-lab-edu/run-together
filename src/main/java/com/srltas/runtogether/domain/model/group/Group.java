package com.srltas.runtogether.domain.model.group;

import java.time.LocalDateTime;
import java.util.Map;

import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.domain.model.user.User;

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
	private final Neighborhood neighborhood;
	private final User createByUser;
	private final LocalDateTime createdAt;

	private final Map<String, User> groupMembers;
}
