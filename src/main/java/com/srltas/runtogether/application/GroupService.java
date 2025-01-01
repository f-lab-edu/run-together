package com.srltas.runtogether.application;

import org.springframework.stereotype.Service;

import com.srltas.runtogether.application.port.in.AddGroup;
import com.srltas.runtogether.application.port.in.AddGroupCommand;
import com.srltas.runtogether.domain.model.group.Group;
import com.srltas.runtogether.domain.model.group.GroupCreationService;
import com.srltas.runtogether.domain.model.group.GroupRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupService implements AddGroup {

	private final GroupCreationService groupCreationService;
	private final GroupRepository groupRepository;

	@Override
	public void create(AddGroupCommand command) {
		Group group = groupCreationService.create(
			command.name(),
			command.description(),
			command.neighborhoodId(),
			command.createByUserId());

		groupRepository.save(group);
	}
}
