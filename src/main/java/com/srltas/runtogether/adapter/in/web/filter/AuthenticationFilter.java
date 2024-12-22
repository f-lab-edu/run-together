package com.srltas.runtogether.adapter.in.web.filter;

import static com.srltas.runtogether.adapter.in.web.common.AuthConstants.*;
import static com.srltas.runtogether.adapter.in.web.common.SessionAttribute.*;
import static com.srltas.runtogether.common.exception.code.CommonErrorCode.*;
import static java.util.Objects.*;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.srltas.runtogether.adapter.in.exception.UnauthorizedException;
import com.srltas.runtogether.adapter.out.session.SessionStorage;
import com.srltas.runtogether.adapter.out.session.UserSession;

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
		UserSession userSession;

		if (isNull(token) || isNull(userSession = sessionStorage.getUserFromSessionId(token))) {
			throw new UnauthorizedException(UNAUTHORIZED_REQUEST);
		}

		HttpSession session = request.getSession(true);
		session.setAttribute(USER_SESSION, userSession);

		filterChain.doFilter(request, response);
	}

	private String extractToken(String authorizationHeader) {
		if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_TOKEN_PREFIX)) {
			return authorizationHeader.substring(BEARER_TOKEN_LENGTH);
		}
		return null;
	}
}
