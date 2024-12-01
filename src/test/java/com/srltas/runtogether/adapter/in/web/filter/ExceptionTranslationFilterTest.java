package com.srltas.runtogether.adapter.in.web.filter;

import static com.srltas.runtogether.common.exception.code.CommonErrorCode.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.srltas.runtogether.adapter.in.exception.UnauthorizedException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

class ExceptionTranslationFilterTest {

	@Test
	@DisplayName("예외가 발생하지 않을 때 필터 처리가 없는지 확인 테스트")
	void testDoFilter_NoException_PassesThrough() throws ServletException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		FilterChain chain = mock(FilterChain.class);

		ExceptionTranslationFilter filter = new ExceptionTranslationFilter();
		filter.doFilter(request, response, chain);

		verify(chain, times(1)).doFilter(request, response);
	}

	@Test
	@DisplayName("UnauthorizedException 처리 테스트")
	void testDoFilter_UnauthorizedException_HandlesException() throws ServletException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		FilterChain chain = mock(FilterChain.class);

		doThrow(new UnauthorizedException(UNAUTHORIZED_REQUEST)).when(chain).doFilter(request, response);

		ExceptionTranslationFilter filter = new ExceptionTranslationFilter();
		filter.doFilter(request, response, chain);

		assertThat(response.getStatus(), is(HttpStatus.UNAUTHORIZED.value()));
		assertThat(response.getContentAsString(), is(containsString(UNAUTHORIZED_REQUEST.getMessage())));
	}

	@Test
	@DisplayName("보안 관련 예외가 아닌 경우 해당 예외를 다시 던지는지 테스트")
	void testDoFilter_OtherException_RethrowsException() throws ServletException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		FilterChain chain = mock(FilterChain.class);

		doThrow(new RuntimeException("Runtime_Exception")).when(chain).doFilter(request, response);

		ExceptionTranslationFilter filter = new ExceptionTranslationFilter();
		RuntimeException exception = assertThrows(RuntimeException.class,
			() -> filter.doFilter(request, response, chain));

		assertThat(exception.getMessage(), is("Runtime_Exception"));
	}
}