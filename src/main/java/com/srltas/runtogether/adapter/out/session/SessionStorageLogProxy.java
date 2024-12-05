package com.srltas.runtogether.adapter.out.session;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.srltas.runtogether.common.log.RunTogetherMDC;

import lombok.RequiredArgsConstructor;

@Repository
@Primary
@RequiredArgsConstructor
public class SessionStorageLogProxy implements SessionStorage {

	private static final String USER_SESSION_FETCH_TIME = "user_session_fetch_time";
	private static final String USER_ID = "userId";

	private final SessionStorage sessionStorage;

	@Override
	public UserSession getUserFromSessionId(String sessionId) {
		long startTime = System.currentTimeMillis();
		UserSession userSession = sessionStorage.getUserFromSessionId(sessionId);
		RunTogetherMDC.putMessage(USER_SESSION_FETCH_TIME, String.valueOf(System.currentTimeMillis() - startTime));
		RunTogetherMDC.put(USER_ID, userSession.userId());
		return userSession;
	}

	@Override
	public void saveUserFromSessionId(String sessionId, UserSession userSessionDTO) {

	}

	@Override
	public void removeUserFromSessionId(String sessionId) {

	}
}
