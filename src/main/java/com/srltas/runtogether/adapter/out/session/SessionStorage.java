package com.srltas.runtogether.adapter.out.session;

import org.springframework.stereotype.Repository;

@Repository
public interface SessionStorage {

	UserSessionDTO getUserFromSessionId(String sessionId);

	void saveUserFromSessionId(String sessionId, UserSessionDTO userSessionDTO);

	void removeUserFromSessionId(String sessionId);
}
