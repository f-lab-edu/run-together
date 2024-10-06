package com.srltas.runtogether.domain.model.user;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
	User findById(long id);
	void save(User user);
}
