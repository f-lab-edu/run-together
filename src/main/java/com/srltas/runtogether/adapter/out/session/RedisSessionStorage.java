package com.srltas.runtogether.adapter.out.session;

import static com.srltas.runtogether.adapter.out.session.converter.UserSessionConverter.*;

import java.util.Objects;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.srltas.runtogether.adapter.out.session.dto.UserSessionDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RedisSessionStorage implements SessionStorage {

	private final RedisTemplate<String, UserSessionDTO> redisTemplate;

	@Override
	public UserSession getUserFromSessionId(String sessionId) {
		UserSessionDTO userSessionDTO = redisTemplate.opsForValue().get(sessionId);
		return toUserSession(Objects.requireNonNull(userSessionDTO));
	}

	@Override
	public void saveUserFromSessionId(String sessionId, UserSession userSession) {
		redisTemplate.opsForValue().set(sessionId, toUserSessionDTO(userSession));
	}

	@Override
	public void removeUserFromSessionId(String sessionId) {
		redisTemplate.delete(sessionId);
	}
}
