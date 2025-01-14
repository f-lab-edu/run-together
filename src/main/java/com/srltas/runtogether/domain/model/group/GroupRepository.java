package com.srltas.runtogether.domain.model.group;

public interface GroupRepository {
	void save(Group group);

	void delete(String groupId);
}
