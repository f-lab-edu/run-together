package com.srltas.runtogether.domain.model.user;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
	Optional<User> findById(long id);
	void save(User user);
}
