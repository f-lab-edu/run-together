package com.srltas.runtogether.adapter.in.web.filter;

import static com.srltas.runtogether.common.AuthConstants.*;
import static com.srltas.runtogether.common.SessionAttribute.USER_SESSION;
import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static java.util.Objects.isNull;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.srltas.runtogether.adapter.out.session.SessionStorage;
import com.srltas.runtogether.adapter.out.session.UserSessionDTO;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

	private final SessionStorage sessionStorage;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws IOException, ServletException {
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		String token = extractToken(authorizationHeader);

		if (isNull(token) || !authenticateUser(token, request)) {
			response.sendError(SC_UNAUTHORIZED, "인증되지 않은 사용자입니다.");
			return;
		}

		filterChain.doFilter(request, response);
	}

	private boolean authenticateUser(String token, HttpServletRequest req) {
		UserSessionDTO userSessionDTO = sessionStorage.getUserFromSessionId(token);
		if (userSessionDTO == null) {
			return false;
		}

		HttpSession session = req.getSession(true);
		session.setAttribute(USER_SESSION, userSessionDTO);
		return true;
	}

	private String extractToken(String authorizationHeader) {
		if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_TOKEN_PREFIX)) {
			return authorizationHeader.substring(BEARER_TOKEN_LENGTH);
		}
		return null;
	}
}
