package com.srltas.runtogether.adapter.out.persistence.mybatis;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.srltas.runtogether.common.log.RunTogetherMDC;
import com.srltas.runtogether.domain.model.group.Group;
import com.srltas.runtogether.domain.model.group.GroupRepository;

import lombok.RequiredArgsConstructor;

@Repository
@Primary
@RequiredArgsConstructor
public class MybatisGroupRepositoryLogProxy implements GroupRepository {

	private final GroupRepository groupRepository;

	@Override
	public void save(Group group) {
		long startTime = System.currentTimeMillis();
		groupRepository.save(group);
		RunTogetherMDC.putMessage("add_group_SQL_time", String.valueOf(System.currentTimeMillis() - startTime));
	}

	@Override
	public void delete(String groupId) {
		long startTime = System.currentTimeMillis();
		groupRepository.delete(groupId);
		RunTogetherMDC.putMessage("delete_group_SQL_time", String.valueOf(System.currentTimeMillis() - startTime));
	}
}
