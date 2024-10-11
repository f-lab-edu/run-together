package com.srltas.runtogether.adapter.in.web.filter;

import static com.srltas.runtogether.common.SessionAttribute.*;

import java.io.IOException;

import com.srltas.runtogether.adapter.out.session.SessionStorage;
import com.srltas.runtogether.adapter.out.session.UserSessionDTO;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationFilter implements Filter {

	private final SessionStorage sessionStorage;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
		IOException,
		ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;

		String authorizationHeader = request.getHeader("Authorization");
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
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			return authorizationHeader.substring(7);
		}
		return null;
	}
}
