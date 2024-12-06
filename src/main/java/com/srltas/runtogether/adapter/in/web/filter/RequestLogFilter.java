package com.srltas.runtogether.adapter.in.web.filter;

import static com.srltas.runtogether.adapter.in.web.common.ApiNameMapper.*;

import java.io.IOException;
import java.util.UUID;

import org.springframework.web.filter.OncePerRequestFilter;

import com.srltas.runtogether.common.log.RunTogetherMDC;

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

		long startTime = System.currentTimeMillis();
		setPreMDC(request, startTime);
		try {
			filterChain.doFilter(request, response);
		} finally {
			setPostMDC(response, startTime);
			log.info("");
			RunTogetherMDC.clear();
		}
	}

	private void setPreMDC(HttpServletRequest request, long startTime) {
		String path = request.getRequestURI();
		String method = request.getMethod();

		RunTogetherMDC.put("traceId", UUID.randomUUID().toString() + startTime + Thread.currentThread().threadId());
		RunTogetherMDC.put("apiName", getApiName(path, method));
		RunTogetherMDC.put("path", path);
		RunTogetherMDC.put("method", method);
		RunTogetherMDC.put("clientIp", request.getRemoteAddr());
	}

	private void setPostMDC(HttpServletResponse response, long startTime) {
		RunTogetherMDC.put("status", String.valueOf(response.getStatus()));
		RunTogetherMDC.putMessage("request_total_time", String.valueOf(System.currentTimeMillis() - startTime));
	}
}
