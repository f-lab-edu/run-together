package com.srltas.runtogether.adapter.out.session;

public interface SessionStorage {

	UserSession getUserFromSessionId(String sessionId);

	void saveUserFromSessionId(String sessionId, UserSession userSession);

	void removeUserFromSessionId(String sessionId);
}
