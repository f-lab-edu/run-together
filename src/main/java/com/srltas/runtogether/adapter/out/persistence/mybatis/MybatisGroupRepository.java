package com.srltas.runtogether.adapter.out.persistence.mybatis;

import static com.srltas.runtogether.adapter.out.persistence.mybatis.converter.GroupConverter.*;

import org.springframework.stereotype.Repository;

import com.srltas.runtogether.domain.model.group.Group;
import com.srltas.runtogether.domain.model.group.GroupRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisGroupRepository implements GroupRepository {

	private final MybatisGroupMapper mapper;

	@Override
	public void save(Group group) {
		mapper.save(toAddGroupDTO(group));
	}
}
