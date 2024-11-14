package com.srltas.runtogether.adapter.out.session;

import static com.srltas.runtogether.adapter.out.session.converter.UserSessionConverter.*;

import java.util.Objects;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.srltas.runtogether.adapter.out.session.dao.UserSessionDAO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RedisSessionStorage implements SessionStorage {

	private final RedisTemplate<String, UserSessionDAO> redisTemplate;

	@Override
	public UserSessionDTO getUserFromSessionId(String sessionId) {
		UserSessionDAO userSessionDAO = redisTemplate.opsForValue().get(sessionId);
		return toUserSessionDTO(Objects.requireNonNull(userSessionDAO));
	}

	@Override
	public void saveUserFromSessionId(String sessionId, UserSessionDTO userSessionDTO) {
		redisTemplate.opsForValue().set(sessionId, toUserSessionDAO(userSessionDTO));
	}

	@Override
	public void removeUserFromSessionId(String sessionId) {
		redisTemplate.delete(sessionId);
	}
}
