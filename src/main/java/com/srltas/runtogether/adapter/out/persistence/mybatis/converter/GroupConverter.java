package com.srltas.runtogether.adapter.out.persistence.mybatis.converter;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dto.AddGroupDTO;
import com.srltas.runtogether.adapter.out.persistence.mybatis.dto.DeleteGroupDTO;
import com.srltas.runtogether.domain.model.group.Group;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GroupConverter {

	public AddGroupDTO toAddGroupDTO(Group group) {
		return AddGroupDTO.builder()
			.groupId(group.getId())
			.groupName(group.getName())
			.description(group.getDescription())
			.neighborhoodId(group.getNeighborhoodId())
			.createByUserId(group.getCreateByUserId())
			.createAt(group.getCreatedAt())
			.build();
	}

	public DeleteGroupDTO toDeleteGroupDTO(String groupId) {
		return DeleteGroupDTO.builder()
			.groupId(groupId)
			.build();
	}
}
