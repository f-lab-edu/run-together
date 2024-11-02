package com.srltas.runtogether.adapter.out.session;

import java.util.UUID;

import org.springframework.stereotype.Repository;

/**
 * 로그인 기능을 개발하기 전까지 사용하는 테스트용 SessionStorage 구현체입니다.
 */
@Repository
public class SessionStorageImpl implements SessionStorage {

	@Override
	public UserSessionDTO getUserFromSessionId(String sessionId) {
		return new UserSessionDTO("usr_" + UUID.randomUUID(), "user_name");
	}

	@Override
	public void saveUserFromSessionId(String sessionId, UserSessionDTO userSessionDTO) { }

	@Override
	public void removeUserFromSessionId(String sessionId) {	}
}
