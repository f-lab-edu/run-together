package com.srltas.runtogether.domain.model.user;

public interface UserRepository {
	User findById(long id);
	void save(User user);
}
