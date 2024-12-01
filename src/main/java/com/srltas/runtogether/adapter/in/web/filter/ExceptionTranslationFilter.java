package com.srltas.runtogether.adapter.in.web.filter;

import static com.srltas.runtogether.common.exception.code.CommonErrorCode.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.GenericFilterBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srltas.runtogether.adapter.in.exception.UnauthorizedException;
import com.srltas.runtogether.common.exception.ErrorResponse;
import com.srltas.runtogether.common.log.RunTogetherMDC;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionTranslationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
		throws IOException, ServletException {
		doFilter((HttpServletRequest)servletRequest, (HttpServletResponse)servletResponse, filterChain);
	}

	private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch (IOException ex) {
			throw ex;
		} catch (Exception ex) {
			Throwable[] causeChain = determineCauseChain(ex);
			RuntimeException securityException =
				(UnauthorizedException)getFirstThrowableOfType(UnauthorizedException.class, causeChain);

			if (securityException == null) {
				rethrow(ex);
			}

			handleRunTogetherSecurityException(response, request, chain, securityException);
		}
	}

	private void handleRunTogetherSecurityException(HttpServletResponse response, HttpServletRequest request,
		FilterChain chain, RuntimeException exception) throws IOException, ServletException {
		if (exception instanceof UnauthorizedException) {
			handleAuthenticationException(request, response, chain, (UnauthorizedException)exception);
		}
	}

	private void handleAuthenticationException(HttpServletRequest request, HttpServletResponse response,
		FilterChain chain, UnauthorizedException exception) throws IOException, ServletException {
		RunTogetherMDC.put("errorCode", UNAUTHORIZED_REQUEST.getCode());
		RunTogetherMDC.put("errorName", exception.getClass().getSimpleName());
		log.error(exception.getMessage());

		sendStartAuthentication(response, request, chain, exception);
	}

	private void sendStartAuthentication(HttpServletResponse response, HttpServletRequest request, FilterChain chain,
		UnauthorizedException reason) throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");

		String responseBody = new ObjectMapper().writeValueAsString(ErrorResponse.of(UNAUTHORIZED_REQUEST));

		response.getWriter().write(responseBody);
		response.getWriter().flush();
	}

	private void rethrow(Exception ex) throws ServletException {
		if (ex instanceof ServletException) {
			throw (ServletException)ex;
		}
		if (ex instanceof RuntimeException) {
			throw (RuntimeException)ex;
		}

		throw new RuntimeException(ex);
	}

	private Throwable getFirstThrowableOfType(Class<? extends Throwable> throwableType, Throwable[] chain) {
		if (chain != null) {
			for (Throwable t : chain) {
				if (throwableType.isInstance(t)) {
					return t;
				}
			}
		}
		return null;
	}

	private Throwable[] determineCauseChain(Throwable throwable) {
		List<Throwable> chain = new ArrayList<>();
		Throwable currentThrowable = throwable;
		while (currentThrowable != null) {
			chain.add(currentThrowable);
			currentThrowable = currentThrowable.getCause();
		}
		return chain.toArray(new Throwable[0]);
	}
}
