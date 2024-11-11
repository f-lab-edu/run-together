package com.srltas.runtogether.adapter.out.session;

public interface SessionStorage {

	UserSessionDTO getUserFromSessionId(String sessionId);

	void saveUserFromSessionId(String sessionId, UserSessionDTO userSessionDTO);

	void removeUserFromSessionId(String sessionId);
}
