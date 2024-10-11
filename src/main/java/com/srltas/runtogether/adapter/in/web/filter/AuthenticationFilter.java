package com.srltas.runtogether.adapter.in.web.filter;

import static com.srltas.runtogether.common.AuthConstants.*;
import static com.srltas.runtogether.common.SessionAttribute.*;

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
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws
		IOException,
		ServletException {
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		String token = extractToken(authorizationHeader);

		if (token != null) {
			UserSessionDTO userSessionDTO = sessionStorage.getUserFromSessionId(token);
			if (userSessionDTO != null) {
				HttpSession session = request.getSession(true);
				session.setAttribute(USER_SESSION, userSessionDTO);
			}
		}
		filterChain.doFilter(request, response);
	}

	private String extractToken(String authorizationHeader) {
		if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_TOKEN_PREFIX)) {
			return authorizationHeader.substring(BEARER_TOKEN_LENGTH);
		}
		return null;
	}
}
