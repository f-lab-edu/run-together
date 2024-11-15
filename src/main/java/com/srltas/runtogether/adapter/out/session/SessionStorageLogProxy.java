package com.srltas.runtogether.adapter.out.session;

import org.slf4j.MDC;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@Primary
@RequiredArgsConstructor
public class SessionStorageLogProxy implements SessionStorage {

	private final SessionStorage sessionStorage;

	@Override
	public UserSessionDTO getUserFromSessionId(String sessionId) {
		long startTime = System.currentTimeMillis();
		UserSessionDTO userSessionDTO = sessionStorage.getUserFromSessionId(sessionId);
		MDC.put("sessionFetchTime", String.valueOf(System.currentTimeMillis() - startTime));
		MDC.put("userId", userSessionDTO.userId());
		return userSessionDTO;
	}

	@Override
	public void saveUserFromSessionId(String sessionId, UserSessionDTO userSessionDTO) {

	}

	@Override
	public void removeUserFromSessionId(String sessionId) {

	}
}
