package com.srltas.runtogether.adapter.in.web.filter;

import static com.srltas.runtogether.adapter.in.web.common.AuthConstants.*;
import static com.srltas.runtogether.adapter.in.web.common.SessionAttribute.*;
import static jakarta.servlet.http.HttpServletResponse.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.srltas.runtogether.adapter.out.session.SessionStorage;
import com.srltas.runtogether.adapter.out.session.UserSessionDTO;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@ExtendWith(MockitoExtension.class)
class AuthenticationFilterTest {

	@Mock
	private SessionStorage sessionStorage;

	@Mock
	private FilterChain filterChain;

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private HttpSession session;

	@InjectMocks
	private AuthenticationFilter authenticationFilter;

	private static final String VALID_TOKEN = "valid_token";
	private static final String INVALID_TOKEN = "invalid_token";

	@Test
	@DisplayName("유효한 토큰을 통해 사용자가 인증되는지 확인")
	void testValidToken_UserAuthenticated() throws ServletException, IOException {
		// given
		UserSessionDTO userSessionDTO = new UserSessionDTO(123L, "testUserName");
		when(request.getHeader(AUTHORIZATION)).thenReturn(BEARER_TOKEN_PREFIX + VALID_TOKEN);
		when(sessionStorage.getUserFromSessionId(VALID_TOKEN)).thenReturn(userSessionDTO);
		when(request.getSession(true)).thenReturn(session);

		// when
		authenticationFilter.doFilterInternal(request, response, filterChain);

		// then
		verify(session).setAttribute(USER_SESSION, userSessionDTO);
		verify(filterChain).doFilter(request, response);
		verify(response, never()).sendError(anyInt(), anyString());
	}

	@Test
	@DisplayName("유효하지 않은 토큰일 때 인증되지 않음을 확인")
	void testInvalidToken_UserAuthenticated() throws ServletException, IOException {
		// given
		when(request.getHeader(AUTHORIZATION)).thenReturn(BEARER_TOKEN_PREFIX + INVALID_TOKEN);
		when(sessionStorage.getUserFromSessionId(INVALID_TOKEN)).thenReturn(null);

		// when
		authenticationFilter.doFilterInternal(request, response, filterChain);

		verify(response).sendError(SC_UNAUTHORIZED, "인증되지 않은 사용자입니다.");
		verify(filterChain, never()).doFilter(request, response);
	}

	@Test
	@DisplayName("Authorization 헤더가 없는 경우 필터가 인증되지 않음으로 처리하는지 확인")
	void testNoAuthorizationHeader() throws ServletException, IOException {
		// given
		when(request.getHeader(AUTHORIZATION)).thenReturn(null);

		// when
		authenticationFilter.doFilterInternal(request, response, filterChain);

		// then
		verify(response).sendError(SC_UNAUTHORIZED, "인증되지 않은 사용자입니다.");
		verify(filterChain, never()).doFilter(request, response);
	}
}
