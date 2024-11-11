package com.srltas.runtogether.adapter.out.session;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RedisSessionStorage implements SessionStorage {

	private final RedisTemplate<String, String> redisTemplate;

	@Override
	public UserSessionDTO getUserFromSessionId(String sessionId) {
		return new UserSessionDTO(redisTemplate.opsForValue().get(sessionId));
	}

	@Override
	public void saveUserFromSessionId(String sessionId, UserSessionDTO userSessionDTO) {
		redisTemplate.opsForValue().set(sessionId, userSessionDTO.userId());
	}

	@Override
	public void removeUserFromSessionId(String sessionId) {
		redisTemplate.delete(sessionId);
	}
}
