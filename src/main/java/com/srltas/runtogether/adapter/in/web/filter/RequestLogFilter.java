package com.srltas.runtogether.adapter.in.web.filter;

import static com.srltas.runtogether.adapter.in.web.common.AuthConstants.*;
import static com.srltas.runtogether.adapter.in.web.common.SessionUtils.*;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestLogFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		setRequestMDC(request);
		long startTime = System.currentTimeMillis();
		try {
			filterChain.doFilter(request, response);
		} finally {
			setResponseMDC(response, startTime);
			log.info("HTTP Request info");
			MDC.clear();
		}
	}

	private void setRequestMDC(HttpServletRequest request) {
		MDC.put("requestId", UUID.randomUUID().toString());
		MDC.put("userIdToken", extractUserIdToken(request.getHeader(AUTHORIZATION)).orElse("unauthenticated"));
		MDC.put("method", request.getMethod());
		MDC.put("path", request.getRequestURI());
		MDC.put("clientIp", request.getRemoteAddr());
	}

	private void setResponseMDC(HttpServletResponse response, long startTime) {
		MDC.put("durationMs", String.valueOf(System.currentTimeMillis() - startTime));
		MDC.put("statusCode", String.valueOf(response.getStatus()));
	}
}
