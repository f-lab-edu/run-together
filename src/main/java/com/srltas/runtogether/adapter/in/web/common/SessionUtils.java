package com.srltas.runtogether.adapter.in.web.common;

import static com.srltas.runtogether.adapter.in.web.common.AuthConstants.*;
import static com.srltas.runtogether.adapter.in.web.common.SessionAttribute.*;

import java.util.Optional;

import com.srltas.runtogether.adapter.out.session.UserSessionDTO;

import jakarta.servlet.http.HttpSession;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SessionUtils {

	public UserSessionDTO getUserSessionDTO(HttpSession session) {
		return (UserSessionDTO)session.getAttribute(USER_SESSION);
	}

	public Optional<String> extractUserIdToken(String authorizationHeader) {
		if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_TOKEN_PREFIX)) {
			return Optional.of(authorizationHeader.substring(BEARER_TOKEN_LENGTH));
		}
		return Optional.empty();
	}
}
